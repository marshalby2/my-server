package com.book.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.comman.result.Result;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;
import com.book.domain.request.UserRegisterRequest;
import com.book.service.UserService;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.Objects;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:28 AM
 */
@RestController
@RequestMapping("/user")
@Api(tags = "user", value = "用户管理接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @PermitAll
    @PostMapping("/login")
    @ApiOperation(value = "后台登录，返回 token ", httpMethod = "POST")
    public Result login(@RequestBody UserLoginRequest request) {
        var token = userService.login(request);
        if (Objects.isNull(token)) {
            return Result.failed("登录失败");
        }
        return Result.success(token);
    }


    /**
     *  用户注册
     *
     * @param request
     * @return
     */
    @PermitAll
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    public Result register(@RequestBody UserRegisterRequest request) {
        return Result.success(userService.add(request));
    }

    /**
     *  分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/loadByPage")
    @ApiOperation(value = "分页查询用户", httpMethod = "POST")
    public Result<IPage<User>> loadByPage(Page<User> page) {
        return Result.success(this.userService.findByPage(page));
    }

}
