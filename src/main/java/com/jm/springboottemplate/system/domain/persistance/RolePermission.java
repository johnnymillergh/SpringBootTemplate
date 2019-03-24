package com.jm.springboottemplate.system.domain.persistance;

import lombok.Data;

/**
 * Description: Role-Permission relation.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 19:50
 **/
@Data
public class RolePermission {
    /**
     * Role's ID.
     */
    private Long roleId;
    /**
     * Permission's ID.
     */
    private Long permissionId;
}