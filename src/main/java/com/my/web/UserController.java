package com.my.web;

import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.my.comman.jwt.JwtToken;
import com.my.comman.result.Result;
import com.my.domain.bean.Role;
import com.my.domain.bean.User;
import com.my.domain.query.UserQuery;
import com.my.domain.request.UserLoginRequest;
import com.my.domain.request.UserRoleRequest;
import com.my.service.MenuService;
import com.my.service.RoleService;
import com.my.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
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
        JwtToken token = userService.login(request);
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
        String username = principal.getName();
        User user = userService.findByUsername(username);
        Map<String,Object> map = Maps.newHashMap();
        map.put("username", user.getUsername());
        map.put("avatar", user.getAvatar());
        map.put("menus", menuService.getMenuTreeByUserId(user.getId()));
        map.put("roles",
                roleService.getListByUser(user.getId()).stream().map(Role::getCode).collect(Collectors.toList())
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
     * @param query
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "用户管理-分页查询", httpMethod = "GET")
    public Result<IPage<User>> page(UserQuery query) {
        return Result.success(this.userService.getByPage(query));
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

    @PostMapping("/saveRole")
    @ApiOperation(value = "用户管理-保存角色信息", httpMethod = "POST")
    public Result saveRole(@RequestBody UserRoleRequest request) {
        return Result.success(userService.saveRole(request.getUserId(), request.getRoleIds()));
    }

    public void exportExcel(UserQuery query, HttpServerResponse response) {
        ExcelWriter writer = ExcelUtil.getBigWriter();

        List<User> users = userService.getList(query);
        AtomicInteger index = new AtomicInteger(1);
        users.forEach(e -> {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("序号", index.getAndIncrement());
            row.put("姓名", e.getUsername());
            writer.writeRow(row, false);
        });
    }

}
