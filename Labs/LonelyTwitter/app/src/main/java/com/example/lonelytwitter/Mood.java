package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    
    private Date timestamp;
    
    protected Mood() {
        this.timestamp = new Date();
    }

    protected Mood(Date when) {
        this.timestamp = when;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date when) {
        this.timestamp = when;
    }
    
    public long getAgeInMillis() {
        return new Date().getTime() - timestamp.getTime();
    }

    public abstract String expressFeeling();
    
    @Override
    public String toString() {
        return expressFeeling() + " at " + timestamp.toString();
    }
}