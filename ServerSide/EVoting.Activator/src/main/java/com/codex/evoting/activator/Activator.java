package com.codex.evoting.activator;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Activator {

    public Activator(){

    }

    public boolean login(String username, String password){

        if (username.equals("test") && password.equals("test")) {
            return true;
        }

        return false;
    }
}
