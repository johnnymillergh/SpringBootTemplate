package com.jmframework.boot.jmspringbootstarterdomain.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 * <h1>CheckRoleNamePLO</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-19 11:11
 **/
@Data
public class CheckRoleNamePLO {
    /**
     * If id is null, then check for creating role's name; otherwise, check for created role's name
     */
    @Min(value = 1L)
    private Long id;
    @NotEmpty(message = "name is required")
    @Length(max = 50, message = "length of name cannot exceed 50")
    private String name;
}
