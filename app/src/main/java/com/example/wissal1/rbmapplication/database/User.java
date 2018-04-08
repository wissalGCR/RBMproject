package com.example.wissal1.rbmapplication.database;

/**
 * Created by WISSAL1 on 03/04/2018.
 */

public class User {
    private int Id;
    private String username;
    private String email ;
    private String password;

    public User(int id, String username, String email, String password) {
        Id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
