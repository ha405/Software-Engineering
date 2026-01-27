package com.example.lonelytwitter;

import java.util.Date;

public class NormalTweet extends Tweet {

    public NormalTweet(String tweetContent) {
        super(tweetContent);
    }

    public NormalTweet(Date timestamp, String tweetContent) {
        super(timestamp, tweetContent);
    }

    @Override
    public Boolean checkIfImportant() {
        return Boolean.FALSE;
    }
}