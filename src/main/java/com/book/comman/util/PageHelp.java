package com.book.comman.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页帮助类
 *
 */
@Data
@ApiModel(value = "PageHelp", description = "分页帮助类")
public class PageHelp<T> implements Serializable {
    private static final long serialVersionUID = 6493839965199219189L;

    public static final int DEFAULT_PAGENO = 1;
    public static final int DEFAULT_PAGESIZE = 10;

    @ApiModelProperty(value = "当前页")
    private Integer pageNo;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize;

    //@ApiModelProperty(value = "排序列")
    @ApiModelProperty(hidden = true)
    private String orderByField;

    //@ApiModelProperty(value = "排序方式(默认倒序,配合排序列使用)", allowableValues = "true,false")
    @ApiModelProperty(hidden = true)
    private Boolean isAsc;

    public Page<T> getPage() {
        int current = this.pageNo == null ? DEFAULT_PAGENO : this.pageNo;
        int size = this.pageSize == null ? DEFAULT_PAGESIZE : this.pageSize;
        Page<T> page = new Page<T>(current, size);

        if (this.orderByField != null && this.orderByField.length() > 0) {
            //beanFieldToSqlField();
            List<String> orderByFieldList = Lists.newArrayList(this.orderByField);
            // 默认为空或者倒叙排序
            this.isAsc = this.isAsc == null ? false : this.isAsc;
            if (this.isAsc) {
                page.setAscs(orderByFieldList);
            } else {
                page.setDescs(orderByFieldList);
            }
        }
        return page;
    }

    /**
     * 字段转译为sql排序字段
     *
     * @return
     */
    private void beanFieldToSqlField() {
        if (this.orderByField != null && this.orderByField.length() > 0) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < this.orderByField.length(); i++) {
                char c = this.orderByField.charAt(i);
                if (!Character.isLowerCase(c) && i != 0) {
                    builder.append("_").append(Character.toLowerCase(c));
                } else {
                    builder.append(c);
                }
            }
//            setOrderByField(builder.toString());
        }
    }


}
