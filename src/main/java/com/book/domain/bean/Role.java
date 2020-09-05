package com.book.domain.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 5:16 PM
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 7546946961871131590L;

    private Long id;
    private String name;
    private String remark;
    private Date createTime;
    private Date updateTime;

}
