package com.chortle.chortle;

import java.util.List;
import java.util.Set;

/**
 * Created by Conor on 20/03/2016.
 */
public class Group {

    private int id;
    private String groupName, description;
    private Set<User> members, admins;
    private User Owner;
    private List<Task> tasks;

    public Group(){

    }
}
