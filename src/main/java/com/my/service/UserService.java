package com.my.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.my.comman.jwt.JwtToken;
import com.my.domain.bean.User;
import com.my.domain.query.UserQuery;
import com.my.domain.request.UserLoginRequest;

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

    /**
     *  分页 查询
     *
     * @param query
     * @return
     */
    IPage<User> getByPage(UserQuery query);

    List<User> getList(UserQuery query);
}
