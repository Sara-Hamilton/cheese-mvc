package org.launchcode.cheesemvc.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {

    @NotNull
    @Size(min=5, max=15)
    private String username;

    @NotNull
    @Size(min=3, max=50)
    private String email;

    @NotNull
    @Size(min=5, max=25)
    private String password;

    private Date date;

    public User(String username, String email, String password, Date date) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    private int userId;
    private static int nextId = 1;

    public User() {
        userId = nextId;
        nextId++;
        date = new Date();

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    }
