package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.domain.bean.Role;
import com.book.domain.bean.RoleMenu;
import com.book.mapper.RoleMapper;
import com.book.mapper.RoleMenuMapper;
import com.book.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:13 PM
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public boolean saveMenu(Long roleId, List<Long> menuIds) {
        // 删除原有的关联关系
        roleMenuMapper.delete(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, roleId));
        // 保存新的关联关系
        menuIds.forEach(menuId -> {
            roleMenuMapper.insert(RoleMenu.builder().roleId(roleId).menuId(menuId).build());
        });
        return true;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }
}
