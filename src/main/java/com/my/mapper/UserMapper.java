package com.my.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.domain.bean.User;
import com.my.domain.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 10:28 AM
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     *  分页查询(暂时弃用了)
     *
     * @param page
     * @param query
     * @return
     */
    IPage<User> selectByPage(Page<User> page, @Param("query") UserQuery query);
}
