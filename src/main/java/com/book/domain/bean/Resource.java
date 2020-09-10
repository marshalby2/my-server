package com.book.domain.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 7/9/20 4:36 PM
 */
@Data
public class Resource implements Serializable {
    private static final long serialVersionUID = -7995381407523548587L;
    private int id;
    private String name;
    private String url;
    private String description;
    private Date createTime;
    private Date updateTime;
}
