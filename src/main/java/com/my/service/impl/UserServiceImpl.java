package com.my.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.comman.jwt.JwtToken;
import com.my.domain.bean.Role;
import com.my.domain.bean.UserRole;
import com.my.comman.exception.ExceptionFactory;
import com.my.comman.jwt.JwtTokenUtil;
import com.my.domain.bean.User;
import com.my.domain.query.UserQuery;
import com.my.domain.request.UserLoginRequest;
import com.my.mapper.RoleMapper;
import com.my.mapper.UserMapper;
import com.my.mapper.UserRoleMapper;
import com.my.service.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 9:41 AM
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findByUsername(String username) {
        return new LambdaQueryChainWrapper<>(userMapper).eq(User::getUsername, username).one();
    }

    @Override
    public JwtToken login(UserLoginRequest request) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        return new JwtToken("Bearer", token);
    }

    @Override
    @Transactional
    public boolean save(User user) {
        if (Objects.nonNull(user.getId()) && !user.getId().equals(0L)) {
            // 密码加密
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
            user.setUpdateTime(Date.from(Instant.now()));
            userMapper.updateById(user);
        } else {
            // 新增
            // 逻辑校验
            User newUser = this.findByUsername(user.getUsername());
            if (Objects.nonNull(newUser)) {
                ExceptionFactory.build("用户名已存在");
            }
            // 密码加密
            user.setPassword(passwordEncoder.encode(user.getNewPassword()));
            userMapper.insert(user);
        }
        return true;
    }

    @Override
    public boolean saveRole(Long userId, List<Long> roleIds) {
        // 删除原有的用户角色关系
        userRoleMapper.delete(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        // 重新插入新关系
        roleIds.forEach(roleId -> {
            userRoleMapper.insert(UserRole.builder().userId(userId).roleId(roleId).build());
        });
        return true;
    }

    @Override
    public IPage<User> getByPage(UserQuery query) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotEmpty(query.getUsername())) {
            wrapper.eq(User::getUsername, query.getUsername());
        }
        var page = userMapper.selectPage(query.getPage(), wrapper);
        var users = page.getRecords();
        // 设置角色信息
        users.forEach(e -> {
            var roles = roleMapper.getListByUser(e.getId());
            if (CollUtil.isNotEmpty(roles)) {
                e.setRoles(roles.stream().map(Role::getName).collect(Collectors.toList()));
            }
        });
        return page;
    }

    @Override
    public List<User> getList(UserQuery query) {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }
}








