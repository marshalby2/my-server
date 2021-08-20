package com.my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.domain.bean.Menu;
import com.my.domain.query.MenuQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 2:58 PM
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") Long userId);


    /**
     *  分页查询(因为要关联表,所以不得不写SQL)
     *
     * @param page
     * @param query
     * @return
     */
    IPage<Menu> selectByPage(Page<Menu> page, @Param("query") MenuQuery query);

    /**
     *  根据角色查询
     *
     * @param roleId
     * @return
     */
    List<Menu> getListByRole(@Param("roleId") Long roleId);
}
