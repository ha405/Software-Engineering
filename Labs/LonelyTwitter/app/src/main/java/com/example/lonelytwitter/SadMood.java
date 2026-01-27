package com.example.lonelytwitter;

import java.util.Date;

public class SadMood extends Mood {

    public SadMood() {
        super();
    }

    public SadMood(Date moodTimestamp) {
        super(moodTimestamp);
    }

    @Override
    public String describeMood() {
        return "Melancholy";
    }
}