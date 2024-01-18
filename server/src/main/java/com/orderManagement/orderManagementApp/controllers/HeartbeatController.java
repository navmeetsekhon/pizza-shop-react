package com.orderManagement.orderManagementApp.controllers;

import com.orderManagement.orderManagementApp.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="v1/heartbeat")
public class HeartbeatController {
    @GetMapping
    public ApiResponse<String> heartbeat(){
        String response="Server is up and running";
        return new ApiResponse<>(HttpStatus.OK.value(),null,response,null);
    }
}
