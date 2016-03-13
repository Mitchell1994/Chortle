package com.chortle.chortle;

/**
 * Created by Mitchell on 13/03/2016.
 */
public class Group {
    public String groupName;
    public String groupDescription;

    public Group(String groupName,
                String groupDescription){
        super();
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }

    @Override
    public String toString() {
        return "Group[groupname=" + groupName
                + ", firstname=" + groupDescription
                + "]";
    }
}
