package com.book.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.comman.result.Result;
import com.book.domain.bean.Role;
import com.book.domain.bean.User;
import com.book.domain.request.UserLoginRequest;
import com.book.service.MenuService;
import com.book.service.RoleService;
import com.book.service.UserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    @ApiOperation(value = "后台登录，返回 token ", httpMethod = "POST")
    public Result login(@RequestBody UserLoginRequest request) {
        var token = userService.login(request);
        if (Objects.isNull(token)) {
            return Result.failed("用户名或密码错误！");
        }
        return Result.success(token);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "退出", httpMethod = "POST")
    public Result logout() {
        return Result.success("退出成功");
    }

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "查询用户信息", httpMethod = "GET")
    public Result getUserInfo(Principal principal) {
        if (Objects.isNull(principal)) {
            return Result.unauthorized(null);
        }
        var username = principal.getName();
        var user = userService.findByUsername(username);
        Map<String,Object> map = Maps.newHashMap();
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("menus", menuService.getMenuTreeByUserId(user.getId()));
        map.put("roles",
                roleService.getRolesByUserId(user.getId()).stream().map(Role::getName).collect(Collectors.toList())
        );
        return Result.success(map);
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
