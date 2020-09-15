package com.my.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.domain.bean.Role;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:12 PM
 */
public interface RoleService extends IService<Role> {
    /**
     *  保存角色菜单关联数据
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean saveMenu(Long roleId, List<Long> menuIds);

    /**
     *  根据用户ID查询角色信息
     *
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(Long userId);
}
