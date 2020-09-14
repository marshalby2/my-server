package com.book.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.comman.result.Result;
import com.book.domain.bean.Menu;
import com.book.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author marshal
 * @Date 10/9/20 9:55 PM
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理", value = "MenuController")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/page")
    @ApiOperation(value = "菜单管理-分页查询", httpMethod = "POST")
    public Result<IPage<Menu>> page(Page<Menu> page) {
        return Result.success( menuService.page(page));
    }


    @GetMapping("/tree")
    @ApiOperation(value = "菜单管理-获取菜单树", httpMethod = "GET")
    public Result tree(Boolean lazy, Long parentId) {
        return Result.success(menuService.getMenuTree(lazy, parentId));
    }

    @GetMapping("/tree/{userId}")
    @ApiOperation(value = "菜单管理-根据用户ID获取菜单树", httpMethod = "GET")
    public Result tree(@PathVariable Long userId) {
        return Result.success(menuService.getMenuTreeByUserId(userId));
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "菜单管理-详细信息", httpMethod = "GET")
    public Result<Menu> detail(@PathVariable Long id) {
        return Result.success( menuService.getById(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "菜单管理-保存", httpMethod = "POST")
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "菜单管理-删除", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return Result.success(menuService.removeById(id));
    }
}
