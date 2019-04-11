package com.jm.springboottemplate.system.controller;

import com.jm.springboottemplate.system.response.ResponseBodyBean;
import com.jm.springboottemplate.system.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: ApiManagementController, change description here.
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-04-11
 * @time: 10:02
 **/
@RestController
@RequestMapping("/apiManagement")
@Api(value = "Api Management Controller", tags = {"api"})
public class ApiManagementController {
    private final ApiService apiService;

    public ApiManagementController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/getController")
    @ApiOperation(value = "Get controller list", notes = "Get controller list")
    public ResponseBodyBean getController() {
        return ResponseBodyBean.ofSuccess(apiService.getAllControllerClass());
    }

    @GetMapping("/getApiByControllerClass")
    @ApiOperation(value = "Get API by controller", notes = "Get API by controller")
    public ResponseBodyBean getApiByControllerClass(String controllerClass, Integer apiStatus) {
        return ResponseBodyBean.ofSuccess(apiService.getApiByClassFullName(controllerClass, apiStatus));
    }
}