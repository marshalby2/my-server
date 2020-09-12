package com.book.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.comman.constant.Constants;
import com.book.domain.bean.Menu;
import com.book.mapper.MenuMapper;
import com.book.service.MenuService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
