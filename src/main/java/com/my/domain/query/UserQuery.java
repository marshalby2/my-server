package com.my.domain.query;

import com.my.comman.util.PageHelp;
import com.my.domain.bean.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 15/9/20 9:07 AM
 */
@Data
@ApiModel("用户查询参数")
public class UserQuery extends PageHelp<User> {

    private static final long serialVersionUID = -4408399783154752199L;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("角色集合")
    private List<Long> roles;

}
