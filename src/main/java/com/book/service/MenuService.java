package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.domain.bean.Menu;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 3:12 PM
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获取菜单树
     * 1. 不是懒加载情况，查询全部
     * 2. 是懒加载，根据parentId 查询
     *
     * @param lazy
     * @param parentId
     * @return
     */
    List<Menu> getMenuTree(boolean lazy, Long parentId);


    /**
     * 根据用户ID查询对应的菜单树
     *
     * @param userId
     * @return
     */
    List<Menu> getMenuTreeByUserId(Long userId);
}
