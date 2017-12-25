package com.example.theodorabendlin.mysmileandroid.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

@DatabaseTable(tableName = "compliments")
public class Compliment {

    public enum Repeat {
        NONE("None"),
        DAILY("Daily"),
        WEEKLY("Weekly"),
        MONTHLY("Monthly");

        private String value;
        Repeat(String value){
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(foreign = true)
    private UserProfile userProfile;

    @DatabaseField
    private String message;

    @DatabaseField
    private DateTime startDateTime;

    @DatabaseField
    private String rrule;

    @DatabaseField
    private Repeat repeat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getRrule() {
        return rrule;
    }

    public void setRrule(String rrule) {
        this.rrule = rrule;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }
}
