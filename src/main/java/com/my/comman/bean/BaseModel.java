package com.my.comman.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 11/9/20 4:02 PM
 */
@Data
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -426550088014732717L;

    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updateTime;
}
