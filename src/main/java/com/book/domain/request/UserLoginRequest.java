package com.book.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 5/9/20 11:36 AM
 */
@Data
@ApiModel("用户登录")
public class UserLoginRequest {
    @ApiModelProperty("用户名")
    private String username;

//    @Pattern(regexp = "^[0-9a-zA-Z]{6,16}$", message = "密码必须由6-16个字母或数字组成")
//    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}
