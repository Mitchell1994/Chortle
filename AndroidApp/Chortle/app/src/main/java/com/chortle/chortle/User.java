package com.chortle.chortle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mitchell on 5/03/2016.
 */
public class User {
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public String hash;

    public User(String username,
                String firstname,
                String lastname,
                String email,
                String hash){
        super();
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "User[username=" + username
                + ", firstname=" + firstname
                + ", lastname=" + lastname
                + ", email=" + email
                + ", hash=" + hash
                + "]";
    }



}
