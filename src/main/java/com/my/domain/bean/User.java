package com.my.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.my.comman.bean.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 10:15 AM
 */
@Data
@ApiModel("用户信息")
public class User extends BaseModel implements Serializable {
    private static final long serialVersionUID = 295547971163197275L;

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("是否启用")
    private Boolean enable;
    @ApiModelProperty(value = "用户角色信息", hidden = true)
    @TableField(exist = false)
    private List<Role> roles;
}
