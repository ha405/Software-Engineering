package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {

    private static final Boolean PRIORITY_FLAG = Boolean.TRUE;

    public ImportantTweet(String body) {
        super(body);
    }

    public ImportantTweet(String body, Date when) {
        super(body, when);
    }

    @Override
    public Boolean hasPriority() {
        return PRIORITY_FLAG;
    }
    
    public String getFormattedOutput() {
        return "[!] " + fetchText();
    }
}