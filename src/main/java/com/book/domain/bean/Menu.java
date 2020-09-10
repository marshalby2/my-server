package com.book.domain.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 10:15 AM
 */

@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 6485417236040485874L;
    private Long id;
    private String name;
    private String url;
    private String icon;
    private Long parentId;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
