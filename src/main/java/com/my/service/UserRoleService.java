package com.my.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.my.domain.bean.UserRole;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 4:58 PM
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     *  更新
     *
     * @param userRole
     * @return
     */
   boolean update(UserRole userRole);

}
