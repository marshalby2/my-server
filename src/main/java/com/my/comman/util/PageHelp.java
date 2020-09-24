package com.my.comman.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页帮助类
 */
@Data
@ApiModel(value = "PageHelp", description = "分页帮助类")
public class PageHelp<T> implements Serializable {

    private static final long serialVersionUID = 3160820498373120551L;

    public static final long DEFAULT_CURRENT = 1;
    public static final int DEFAULT_SIZE = 10;

    @ApiModelProperty(value = "当前页")
    private Long current;

    @ApiModelProperty(value = "分页大小")
    private Long size;

    @ApiModelProperty(value = "排序列", hidden = true)
    private List<String> orderFields;

    @ApiModelProperty(value = "排序方式(默认升序,配合排序列使用)", allowableValues = "true,false", hidden = true)
    private List<Boolean> isAsc;

    public Page<T> getPage() {

        long current = this.current == null ? DEFAULT_CURRENT : this.current;
        long size = this.size == null ? DEFAULT_SIZE : this.size;

        Page<T> page = new Page<T>(current, size);
        // 设置排序字段
//        List<OrderItem> orderItems = Lists.newArrayList();
//        if (CollUtil.isNotEmpty(orderFields)) {
//            for (int i = 0; i < orderFields.size(); i ++) {
//                if (CollUtil.isNotEmpty(isAsc) && isAsc.get(i) != null && isAsc.get(i)) {
//                    orderItems.add(OrderItem.desc(orderFields.get(i)));
//                } else {
//                    orderItems.add(OrderItem.asc(orderFields.get(i)));
//                }
//            }
//        }
//        page.setOrders(orderItems);
        return page;
    }

}
