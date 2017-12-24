package com.example.theodorabendlin.mysmileandroid.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user_profile")
public class UserProfile {

    public static final String DEFAULT_UUID = "1234";

    @DatabaseField(id = true)
    private String uuid;

    @DatabaseField
    private String firstName;

    @DatabaseField
    private String lastName;

    @DatabaseField
    private String email;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
