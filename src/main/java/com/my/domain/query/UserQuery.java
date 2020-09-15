package com.my.domain.query;

import com.my.comman.util.PageHelp;
import com.my.domain.bean.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 15/9/20 9:07 AM
 */
@Data
@ApiModel("用户查询参数")
public class UserQuery extends PageHelp<User> {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("备注")
    private String remark;

}
