package com.my.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.comman.constant.Constants;
import com.my.domain.bean.Menu;
import com.my.domain.query.MenuQuery;
import com.my.mapper.MenuMapper;
import com.my.service.MenuService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:13 PM
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuTree(boolean lazy, Long parentId) {
        if (!lazy) {
            return recursiveCreateTree(menuMapper.selectList(
                    Wrappers.<Menu>lambdaQuery().orderByAsc(Menu::getSort)),
                    Constants.MENU_TREE_ROOT_ID);
        }
        Long parent = parentId == null ? Constants.MENU_TREE_ROOT_ID : parentId;
        return recursiveCreateTree(menuMapper.selectList(
                Wrappers.<Menu>lambdaQuery().eq(Menu::getParentId, parent).orderByAsc(Menu::getSort)),
                parentId);
    }

    @Override
    public List<Menu> getMenuTreeByUserId(Long userId) {
        return recursiveCreateTree(menuMapper.getMenusByUserId(userId), Constants.DEFAULT_MENU_TREE_ROOT_ID);
    }

    @Override
    public IPage<Menu> getByPage(MenuQuery query) {
        return menuMapper.selectByPage(query.getPage(), query);
    }


    /**
     * 递归创建菜单树
     *
     * @param menus
     * @param parentId
     * @return
     */
    private List<Menu> recursiveCreateTree(List<Menu> menus, Long parentId) {
        List<Menu> menuList = Lists.newArrayList();
        menus.forEach(menu -> {
            if (menu.getParentId().equals(parentId)) {
                menuList.add(findChildren(menu, menus));
            }
        });
        return menuList;
    }

    /**
     * 查找子菜单
     *
     * @param menu
     * @param menus
     * @return
     */
    private Menu findChildren(Menu menu, List<Menu> menus) {
        menus.forEach(m -> {
            if (menu.getId().equals(m.getParentId())) {
                menu.getChildren().add(findChildren(m, menus));
            }
        });
        return menu;
    }
}
