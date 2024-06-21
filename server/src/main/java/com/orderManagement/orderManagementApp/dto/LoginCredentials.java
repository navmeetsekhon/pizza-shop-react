package com.orderManagement.orderManagementApp.dto;

import lombok.Data;

@Data
public class LoginCredentials {
    private String username;
    private String password;

    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }

}
