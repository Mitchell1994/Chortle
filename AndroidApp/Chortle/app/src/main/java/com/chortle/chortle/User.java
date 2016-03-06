package com.chortle.chortle;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mitchell on 5/03/2016.
 */
public class User {
    public String _username;
    public String _firstname;
    public String _lastname;
    public String _email;
    public String _hash;

    public User(String username,
                String firstname,
                String lastname,
                String email,
                String hash){
        _username = username;
        _firstname = firstname;
        _lastname = lastname;
        _email = email;
        _hash = hash;
    }

    public JSONObject toJson() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", _username);
        map.put("firstname", _firstname);
        map.put("lastname", _lastname);
        map.put("email", _email);
        map.put("hash", _hash);
        return new JSONObject(map);
    }
}
