package com.example.lightdance.logintest.model.login;

/**
 * 实体类，帐密和一个“用户住址”
 *
 * @author LightDance
 * @date 2018/3/4.
 */

public class UserBean {
    private String username;
    private  String userpwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}
