package com.my.comman.security;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.my.domain.bean.Role;
import com.my.domain.bean.User;
import com.my.mapper.RoleMapper;
import com.my.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author marshal
 * @Date 7/9/20 3:28 PM
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = new LambdaQueryChainWrapper<>(userMapper).eq(User::getUsername, username).one();
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        // 设置权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        var roles = roleMapper.getListByUser(user.getId());
        if (roles != null) {
            authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toList());
        }
        return new LoginUser(user.getUsername(), user.getPassword(), authorities);
    }
}
