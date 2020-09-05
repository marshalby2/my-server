package com.book.domain.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 10:15 AM
 */

@Data
public class Menu{
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
