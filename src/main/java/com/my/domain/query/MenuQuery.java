package com.my.domain.query;

import com.my.comman.util.PageHelp;
import com.my.domain.bean.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 15/9/20 3:41 PM
 */
@Data
@ApiModel("菜单查询参数类")
public class MenuQuery extends PageHelp<Menu> {
    private static final long serialVersionUID = 1429218960957708271L;
    @ApiModelProperty("菜单名称")
    private String label;

}
