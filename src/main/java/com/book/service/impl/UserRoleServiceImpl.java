package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.domain.bean.UserRole;
import com.book.mapper.UserRoleMapper;
import com.book.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 4:59 PM
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean update(UserRole userRole) {
        // 删除原有的关联关系
        var wrapper = new LambdaQueryWrapper<UserRole>();
        wrapper.eq(UserRole::getUserId, userRole.getUserId());
        userRoleMapper.delete(wrapper);

        userRoleMapper.insert(userRole);
        return false;
    }
}
