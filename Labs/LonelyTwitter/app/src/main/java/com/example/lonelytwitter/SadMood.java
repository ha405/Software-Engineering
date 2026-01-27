package com.example.lonelytwitter;

import java.util.Date;

public class SadMood extends Mood {

    private static final String FEELING = "Gloomy";

    public SadMood() {
        super();
    }

    public SadMood(Date when) {
        super(when);
    }

    @Override
    public String expressFeeling() {
        return FEELING;
    }
    
    public boolean isPositive() {
        return false;
    }
}