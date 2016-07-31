package com.codex.evoting.admin;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Admin {

    public Admin(){

    }

    public boolean login(String username, String password){

        if (username.equals("test") && password.equals("test")) {
            return true;
        }

        return false;
    }
}
