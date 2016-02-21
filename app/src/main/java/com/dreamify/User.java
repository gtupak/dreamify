package com.dreamify;

/**
 * Created by aaronpersaud on 2016-02-20.
 */
public class User {
    private String username;
    private int ID = 0;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.ID += 1;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }
}

