package com.example.lonelytwitter;

import java.util.Date;

public class ImportantTweet extends Tweet {

    public ImportantTweet(String tweetContent) {
        super(tweetContent);
    }

    public ImportantTweet(Date timestamp, String tweetContent) {
        super(timestamp, tweetContent);
    }

    @Override
    public Boolean checkIfImportant() {
        return Boolean.TRUE;
    }
}