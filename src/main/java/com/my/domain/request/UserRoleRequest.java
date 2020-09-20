package com.my.domain.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @Description TODO
 * @Author marshal
 * @Date 20/9/20 11:36 AM
 */
@Data
@ApiModel("保存用户角色信息")
public class UserRoleRequest {
    private Long userId;
    private List<Long> roleIds;
}
