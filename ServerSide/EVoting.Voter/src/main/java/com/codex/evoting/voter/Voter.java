package com.codex.evoting.voter;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Voter {

    public Voter(){

    }

    public boolean login(String username, String password){

        if (username.equals("test") && password.equals("test")) {
            return true;
        }

        return false;
    }
}
