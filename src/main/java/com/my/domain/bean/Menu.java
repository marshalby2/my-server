package com.my.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.my.comman.bean.BaseModel;
import com.google.common.collect.Lists;
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
@ApiModel("菜单信息")
public class Menu extends BaseModel implements Serializable {
    private static final long serialVersionUID = 6485417236040485874L;
    @ApiModelProperty("名称")
    private String label;
    @ApiModelProperty("URL")
    private String url;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("上级菜单ID")
    private Long parentId;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty(value = "子菜单", hidden = true)
    @TableField(exist = false)
    private List<Menu> children = Lists.newArrayList();
    @ApiModelProperty("上级菜单名称")
    @TableField(exist = false)
    private String parent;
}
