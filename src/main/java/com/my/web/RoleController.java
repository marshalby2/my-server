package com.my.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.domain.bean.Role;
import com.my.comman.result.Result;
import com.my.domain.query.RoleQuery;
import com.my.domain.request.RoleMenuRequest;
import com.my.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:15 PM
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理", value = "RoleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/page")
    @ApiOperation(value = "角色管理-分页查询", httpMethod = "GET")
    public Result<IPage<Role>> page(RoleQuery query) {
        return Result.success(roleService.getByPage(query));
    }

    @GetMapping("/list")
    @ApiOperation(value = "角色管理-查询所有", httpMethod = "GET")
    public Result<List<Role>> list() {
        return Result.success(roleService.list());
    }

    @GetMapping("/list/{userId}")
    @ApiOperation(value = "角色管理-根据用户ID查询", httpMethod = "GET")
    public Result<List<Long>> getListByUser(@PathVariable Long userId) {
        return Result.success(roleService.getListByUser(userId).stream().map(Role::getId).collect(Collectors.toList()));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "角色管理-详细信息", httpMethod = "GET")
    public Result<Role> detail(@PathVariable Long id) {
        return Result.success(roleService.getById(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "角色管理-保存", httpMethod = "POST")
    public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "角色管理-删除", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return Result.success(roleService.removeById(id));
    }

    @PostMapping("/saveMenu/")
    @ApiOperation(value = "角色管理-保存菜单信息", httpMethod = "POST")
    public Result saveRole(@RequestBody RoleMenuRequest request) {
        return Result.success(roleService.saveMenu(request.getRoleId(), request.getMenuIds()));
    }
}
