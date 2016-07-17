package com.codex.evoting.database;

/**
 * Created by Andreas on 2016/07/17.
 */
public class Database {

    public Database(){

    }

    public boolean login(String username, String password){

        if (username.equals("test") && password.equals("test")) {
            return true;
        }

        return false;
    }
}
