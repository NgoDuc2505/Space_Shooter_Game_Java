package com.spaceshooter.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 4286884142032940064L;
    private String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
}
