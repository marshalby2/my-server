package com.book.domain.bean;

import com.book.comman.bean.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 5:16 PM
 */
@Data
@ApiModel("角色信息")
public class Role extends BaseModel implements Serializable {
    private static final long serialVersionUID = 7546946961871131590L;
    @ApiModelProperty("名称")
    private String name;
}
