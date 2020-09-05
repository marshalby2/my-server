package com.book.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
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
    String login(UserLoginRequest request);

    User register(UserRegisterRequest request);

    IPage<User> findByPage(Page<User> page);
}
