package com.example.lonelytwitter;

import java.util.Date;

public class NormalTweet extends Tweet {

    private static final Boolean PRIORITY_FLAG = Boolean.FALSE;

    public NormalTweet(String body) {
        super(body);
    }

    public NormalTweet(String body, Date when) {
        super(body, when);
    }

    @Override
    public Boolean hasPriority() {
        return PRIORITY_FLAG;
    }
    
    public String getFormattedOutput() {
        return fetchText();
    }
}