package com.book.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.comman.result.Result;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;
import com.book.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Objects;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:28 AM
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理", value = "UserController")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/login")
    @ApiOperation(value = "后台登录，返回 token ", httpMethod = "POST")
    public Result login(@RequestBody UserLoginRequest request) {
        var token = userService.login(request);
        if (Objects.isNull(token)) {
            return Result.failed("用户名或密码错误！");
        }
        return Result.success(token);
    }


    @PostMapping("/save")
    @ApiOperation(value = "用户管理-保存", httpMethod = "POST")
    public Result save(@RequestBody User user) {
        return Result.success(userService.save(user));
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/page")
    @ApiOperation(value = "用户管理-分页查询", httpMethod = "POST")
    public Result<IPage<User>> page(Page<User> page) {
        return Result.success(this.userService.page(page));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "用户管理-详细信息", httpMethod = "GET")
    public Result<User> detail(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "用户管理-删除", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return Result.success(userService.removeById(id));
    }

    @GetMapping("/saveRole")
    @ApiOperation(value = "用户管理-保存角色信息", httpMethod = "GET")
    public Result saveRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") List<Long> roleIds) {
        return Result.success(userService.saveRole(userId, roleIds));
    }

}
