package com.lifuyi.dev_monitor.model.login;

import com.lifuyi.dev_monitor.model.user.User;

import java.io.Serializable;

public class LoginResult implements Serializable {
    private static final long serialVersionUID = -7704828868930783816L;
    private User user;
    private String token;

    public LoginResult() {
    }

    public LoginResult(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
