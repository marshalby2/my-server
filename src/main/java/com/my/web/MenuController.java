package com.my.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.my.comman.result.Result;
import com.my.domain.bean.Menu;
import com.my.domain.query.MenuQuery;
import com.my.service.MenuService;
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

    @GetMapping("/page")
    @ApiOperation(value = "菜单管理-分页查询", httpMethod = "GET")
    public Result<IPage<Menu>> page(MenuQuery query) {
        return Result.success(menuService.getByPage(query));
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
        return Result.success(menuService.getById(id));
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
