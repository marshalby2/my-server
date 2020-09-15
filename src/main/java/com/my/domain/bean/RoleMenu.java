package com.my.domain.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author marshal
 * @Date 7/9/20 4:25 PM
 */
@Getter
@Setter
@Builder
@ApiModel("角色菜单关系表")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -97261782917547937L;
    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("角色ID")
    private Long roleId;
    @ApiModelProperty("菜单ID")
    private Long menuId;
}
