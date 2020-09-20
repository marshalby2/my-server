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
@ApiModel("保存角色菜单信息")
public class RoleMenuRequest {
    private Long roleId;
    private List<Long> menuIds;
}
