package com.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.comman.jwt.JwtToken;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;
import com.book.domain.request.UserRegisterRequest;

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
     *  新增用户
     *
     * @param request
     * @return
     */
    User add(UserRegisterRequest request);

    /**
     *  分页查询
     *
     * @param page
     * @return
     */
    IPage<User> findByPage(Page<User> page);
}
