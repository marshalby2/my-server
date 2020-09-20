package com.my.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.comman.exception.ExceptionFactory;
import com.my.domain.bean.Role;
import com.my.domain.bean.RoleMenu;
import com.my.domain.query.RoleQuery;
import com.my.mapper.RoleMapper;
import com.my.mapper.RoleMenuMapper;
import com.my.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public List<Role> getListByUser(Long userId) {
        return roleMapper.getListByUser(userId);
    }

    public boolean save(Role role) {

        if (Objects.nonNull(role.getId()) && !role.getId().equals(0L)) {
            // 更新
            roleMapper.updateById(role);
        } else {
            // 新增
            var one = roleMapper.selectOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, role.getName()));
            if (Objects.nonNull(one)) {
                ExceptionFactory.build("角色名称已存在");
            }
            roleMapper.insert(role);
        }

        return true;
    }

    @Override
    public IPage<Role> getByPage(RoleQuery query) {
        var wrapper = Wrappers.<Role>lambdaQuery();
        if (StrUtil.isNotEmpty(query.getName())) {
            wrapper.like(Role::getName, query.getName());
        }
        if (StrUtil.isNotEmpty(query.getCode())) {
            wrapper.like(Role::getName, query.getName());
        }
        return roleMapper.selectPage(query.getPage(), wrapper);
    }
}
