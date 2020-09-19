package com.my.domain.query;

import com.my.comman.util.PageHelp;
import com.my.domain.bean.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 15/9/20 3:41 PM
 */
@Data
@ApiModel("角色查询参数类")
public class RoleQuery extends PageHelp<Role> {
    private static final long serialVersionUID = 1429218960957708271L;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("标识")
    private String code;
}
