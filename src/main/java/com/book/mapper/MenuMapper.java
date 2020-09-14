package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.domain.bean.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 2:58 PM
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(@Param("userId") Long userId);

}
