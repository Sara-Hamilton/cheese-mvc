package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {

    static ArrayList<User> users = new ArrayList<>();
    //add
    public static void add(User newUser) {
        users.add(newUser);
    }

    //getAll
    public static ArrayList<User> getAll() {
        return users;
    }



    //getById
    public User getById() {
        User aUser = null;

        for (User someUser : users); {
        if(someUser.getUserId() == id) {
            aUser = someUser;
        }
            return aUser;
        }
    }
}
