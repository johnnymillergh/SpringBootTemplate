package com.jmframework.boot.jmspringbootstarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.role.payload.CheckRoleNamePLO;
import com.jmframework.boot.jmspringbootstarterdomain.role.payload.CreateRolePLO;
import com.jmframework.boot.jmspringbootstarterdomain.role.payload.EditRolePLO;
import com.jmframework.boot.jmspringbootstarterdomain.role.payload.GetRoleListPLO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarterdomain.role.response.GetRoleListRO;
import com.jmframework.boot.jmspringbootstarterdomain.role.response.SearchRoleRO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <h1>RoleController</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-05-18 12:22
 **/
@Slf4j
@RestController
@RequestMapping("/role")
@Api(tags = {"/role"})
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/get-list")
    @ApiOperation(value = "/get-list", notes = "Get role page list")
    public ResponseBodyBean<GetRoleListRO> getList(@Valid @RequestBody GetRoleListPLO plo) {
        Page<RolePO> page = new Page<>(plo.getCurrentPage(), plo.getPageSize());
        List<RolePO> poList = roleService.getList(page);
        GetRoleListRO ro = new GetRoleListRO();
        poList.forEach(item -> {
            GetRoleListRO.Role role = new GetRoleListRO.Role();
            BeanUtil.copyProperties(item, role);
            ro.getRoleList().add(role);
        });
        return ResponseBodyBean.ofSuccess(ro);
    }

    @PostMapping("/check-role-name")
    @ApiOperation(value = "/check-role-name", notes = "To ensure the uniqueness of name of role")
    public ResponseBodyBean checkRoleName(@Valid @RequestBody CheckRoleNamePLO plo) {
        plo.setName(roleService.handleRoleName(plo.getName()));
        RolePO po = new RolePO();
        BeanUtil.copyProperties(plo, po);
        if (roleService.checkRoleName(po)) {
            return ResponseBodyBean.ofSuccess("Tne name of role is available");
        }
        return ResponseBodyBean.ofFailure("Tne name of role is not available");
    }

    @PostMapping("/create-role")
    @ApiOperation(value = "/create-role", notes = "Create a role")
    public ResponseBodyBean createRole(@Valid @RequestBody CreateRolePLO plo) {
        plo.setName(roleService.handleRoleName(plo.getName()));
        RolePO po = new RolePO();
        BeanUtil.copyProperties(plo, po);
        if (roleService.insertRole(po)) {
            return ResponseBodyBean.ofSuccess("Role created successfully");
        }
        return ResponseBodyBean.ofFailure("Role cannot be created");
    }

    @GetMapping("/search-role")
    @ApiOperation(value = "/search-role", notes = "Search role by name")
    public ResponseBodyBean<SearchRoleRO> searchRole(String roleName) {
        if (StringUtils.isBlank(roleName)) {
            return ResponseBodyBean.ofFailure("The name of role is not blank");
        }
        roleName = roleService.handleRoleName(roleName);
        RolePO po = roleService.searchRole(roleName);
        if (po == null) {
            return ResponseBodyBean.setResponse(HttpStatus.WARNING.getCode(), "No result", null);
        }
        SearchRoleRO ro = new SearchRoleRO();
        BeanUtil.copyProperties(po, ro);
        return ResponseBodyBean.ofSuccess(ro);
    }

    @PostMapping("/edit-role")
    @ApiOperation(value = "/edit-role", notes = "Edit role")
    public ResponseBodyBean editRole(@Valid @RequestBody EditRolePLO plo) {
        RolePO po = new RolePO();
        BeanUtil.copyProperties(plo, po);
        boolean status = roleService.updateRole(po);
        if (status) {
            return ResponseBodyBean.ofSuccess(String.format("Role updated (%s)", plo.getName()));
        }
        return ResponseBodyBean.ofFailure(String.format("Update failure (%s)", plo.getName()));
    }
}
