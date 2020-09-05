package com.book.domain.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 10:15 AM
 */
@Data
public class Book {
    private Long id;
    private String name;
    private String author;
    private String image;
    private Float price;
    private int version;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
