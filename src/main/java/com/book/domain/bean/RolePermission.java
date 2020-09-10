package com.book.domain.bean;

import lombok.Data;

/**
 * @Description TODO
 * @Author marshal
 * @Date 7/9/20 5:18 PM
 */
@Data
public class RolePermission {
    private int id;
    private int roleId;
    private int permissionId;
}
