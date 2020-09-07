package com.book.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author marshal
 * @Date 4/9/20 10:15 AM
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String username;
    private String password;
    private String avatar;
    private String nickName;
    private String email;
    private String remark;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    @TableField(exist = false)
    private List<Role> roles;
}
