package com.example.springbootdieta.model;

import java.util.List;

public class AuthResponse {
    private String userName;

    private List<String> roles;

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public List<String> getRoles(){
        return roles;
    }

    public void setRoles(List<String> roles){
        this.roles = roles;
    }
}
