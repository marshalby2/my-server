package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.comman.exception.ExceptionFactory;
import com.book.comman.jwt.JwtToken;
import com.book.comman.jwt.JwtTokenUtil;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;
import com.book.domain.request.UserRegisterRequest;
import com.book.mapper.UserMapper;
import com.book.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public User findByUsername(String username) {
        var wrapper = new LambdaQueryWrapper<User>();
        wrapper.eq(User::getUsername, username);
        return this.userMapper.selectOne(wrapper);
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
        return  new JwtToken("Bearer", token);
    }

    @Override
    public User add(UserRegisterRequest request) {
        var newUser = new User();
        BeanUtils.copyProperties(request, newUser);
        newUser.setCreateTime(new Date());
        newUser.setStatus(1);

        var user = this.findByUsername(newUser.getUsername());
        if (Objects.nonNull(user)) {
            ExceptionFactory.build("用户名已存在");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userMapper.insert(newUser);
        return newUser;
    }

    @Override
    public IPage<User> findByPage(Page<User> page) {
        return userMapper.selectPage(page, new QueryWrapper<User>());
    }
}








