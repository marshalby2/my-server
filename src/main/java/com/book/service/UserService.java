package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.comman.jwt.JwtToken;
import com.book.domain.bean.Role;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 9:41 AM
 */
public interface UserService extends IService<User>{


    /**
     *  根据用户名查询
     *
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 登录
     *
     * @param request
     * @return
     */
    JwtToken login(UserLoginRequest request);

    /**
     *  保存用户
     *
     * @param user
     * @return
     */
    boolean save(User user);

    /**
     *  保存角色信息
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveRole(Long userId, List<Long> roleIds);

}
