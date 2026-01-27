package com.example.lonelytwitter;

import java.util.Date;

public abstract class Tweet implements Tweetable {
    
    private Date creationTimestamp;
    private String content;

    public Tweet(String tweetContent) {
        this.content = tweetContent;
        this.creationTimestamp = new Date();
    }

    public Tweet(Date timestamp, String tweetContent) {
        this.creationTimestamp = timestamp;
        this.content = tweetContent;
    }

    @Override
    public Date retrieveTimestamp() {
        return this.creationTimestamp;
    }

    public void updateTimestamp(Date newTimestamp) {
        this.creationTimestamp = newTimestamp;
    }

    @Override
    public String retrieveContent() {
        return this.content;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public abstract Boolean checkIfImportant();
}