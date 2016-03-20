package com.chortle.chortle;

/**
 * Created by Mitchell on 13/03/2016.
 */
public class GroupDTO {
    private String groupName;
    private String groupDescription;
    private final int id;

    public GroupDTO(String groupName,
                    String groupDescription, int id){
        super();
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.id = id;
    }

    public GroupDTO(String groupName, String groupDescription){
        this(groupName,groupDescription, -1);
    }

    @Override
    public String toString() {
        return "GroupDTO[groupname=" + groupName
                + ", firstname=" + groupDescription
                + "]";
    }
}
