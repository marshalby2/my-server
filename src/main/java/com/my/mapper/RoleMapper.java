package com.my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.domain.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:28 AM
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getListByUser(@Param("userId") Long userId);
}
