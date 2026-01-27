package com.example.lonelytwitter;

import java.util.Date;

public class HappyMood extends Mood {

    private static final String FEELING = "Cheerful";

    public HappyMood() {
        super();
    }

    public HappyMood(Date when) {
        super(when);
    }

    @Override
    public String expressFeeling() {
        return FEELING;
    }
    
    public boolean isPositive() {
        return true;
    }
}