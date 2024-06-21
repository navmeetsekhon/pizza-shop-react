package com.orderManagement.orderManagementApp.controllers;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.orderManagementApp.dto.LoginCredentials;
import com.orderManagement.orderManagementApp.utils.ApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

    final static private String adminUsername = "admin";
    final static private String adminPassword = "password";
    @PostMapping("/adminLogin")
    public ApiResponse<Boolean> adminLogin(@RequestBody LoginCredentials loginCredentials){
        if(loginCredentials.getUsername() == adminPassword && loginCredentials.getPassword() == adminPassword){
            return new ApiResponse<>(200,true);
        }
        else{
            return new ApiResponse<>(400,false);
        }
    } 

    static public Map<String,String> userCredentials = new HashMap<>();

    
    @PostMapping("/userlogin")
public ApiResponse<Boolean> userLogin(@RequestBody LoginCredentials loginCredentials){
    String storedPassword = userCredentials.get(loginCredentials.getUsername());
    if(storedPassword != null && storedPassword.equals(loginCredentials.getPassword())) {
        return new ApiResponse<>(200,true);
    } else {
        return new ApiResponse<>(400,false);
    }
//     @PostMapping("/userlogin")
// public ApiResponse<Boolean> userLogin(@RequestBody LoginCredentials loginCredentials){
//     String storedPassword = userCredentials.get(loginCredentials.getUsername());
//     if(storedPassword != null && storedPassword.equals(loginCredentials.getPassword())) {
//         return new ApiResponse<>(200,true);
//     } else {
//         return new ApiResponse<>(400,false);
//     }
}

    
}
