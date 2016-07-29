package com.codex.evoting.party;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Party {

    public Party(){

    }

    public boolean login(String username, String password){

        if (username.equals("test") && password.equals("test")) {
            return true;
        }

        return false;
    }
}
