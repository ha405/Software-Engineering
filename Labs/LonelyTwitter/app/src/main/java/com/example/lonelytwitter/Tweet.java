package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    
    private String textBody;
    private Date postedOn;

    protected Tweet(String body) {
        this.textBody = body;
        this.postedOn = new Date();
    }

    protected Tweet(String body, Date when) {
        this.textBody = body;
        this.postedOn = when;
    }

    @Override
    public String fetchText() {
        return textBody;
    }

    @Override
    public Date fetchCreatedOn() {
        return postedOn;
    }

    public void modifyText(String newBody) {
        this.textBody = newBody;
    }

    public void modifyPostedOn(Date newDate) {
        this.postedOn = newDate;
    }

    public int calculateLength() {
        return this.textBody != null ? this.textBody.length() : 0;
    }

    @Override
    public abstract Boolean hasPriority();
    
    @Override
    public String toString() {
        return textBody + " [" + postedOn.toString() + "]";
    }
}