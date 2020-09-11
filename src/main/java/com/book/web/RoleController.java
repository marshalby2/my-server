package com.book.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.comman.result.Result;
import com.book.domain.bean.Role;
import com.book.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/page")
    @ApiOperation(value = "角色管理-分页查询", httpMethod = "POST")
    public Result<IPage<Role>> page(Page<Role> page) {
        return Result.success(roleService.page(page));
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

    @GetMapping("/saveMenu/")
    @ApiOperation(value = "角色管理-保存菜单信息", httpMethod = "GET")
    public Result saveRole(@RequestParam("roleId") Long roleId, @RequestParam("menuIds") List<Long> menuIds) {
        return Result.success(roleService.saveMenu(roleId, menuIds));
    }
}