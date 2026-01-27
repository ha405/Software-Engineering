package com.example.lonelytwitter;

import java.util.Date;

public class HappyMood extends Mood {

    public HappyMood() {
        super();
    }

    public HappyMood(Date moodTimestamp) {
        super(moodTimestamp);
    }

    @Override
    public String describeMood() {
        return "Joyful";
    }
}