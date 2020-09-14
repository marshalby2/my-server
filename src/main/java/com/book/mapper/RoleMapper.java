package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.domain.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:28 AM
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByUserId(@Param("userId") Long userId);
}
