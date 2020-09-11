package com.book.domain.bean;

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
@ApiModel("用户角色关系表")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 506589750957563417L;
    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("角色ID")
    private Long roleId;
}
