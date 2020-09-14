package com.book.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.comman.exception.ExceptionFactory;
import com.book.comman.jwt.JwtToken;
import com.book.comman.jwt.JwtTokenUtil;
import com.book.domain.bean.Role;
import com.book.domain.bean.User;
import com.book.domain.bean.UserRole;
import com.book.domain.request.UserLoginRequest;
import com.book.mapper.UserMapper;
import com.book.mapper.UserRoleMapper;
import com.book.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public User findByUsername(String username) {
        var query = new LambdaQueryChainWrapper<>(userMapper);
        query.eq(User::getUsername, username);
        return query.one();
    }

    @Override
    public JwtToken login(UserLoginRequest request) {
        String token = null;
        try {
            var userDetails = userDetailsService.loadUserByUsername(request.getUsername());
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
    public boolean save(User user) {
        // 逻辑校验
        var newUser = this.findByUsername(user.getUsername());
        if (Objects.nonNull(newUser)) {
            ExceptionFactory.build("用户名已存在");
        }
        if (Objects.nonNull(user.getId()) && !user.getId().equals(0L)) {
            // 更新
            if (StrUtil.isNotEmpty(user.getPassword())) {
                // 密码加密
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            user.setUpdateTime(Date.from(Instant.now()));
            userMapper.updateById(user);
        } else {
            // 新增
            // 密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
}








