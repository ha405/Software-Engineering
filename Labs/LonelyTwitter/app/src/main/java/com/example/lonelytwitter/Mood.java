package com.example.lonelytwitter;

import java.util.Date;

public abstract class Mood {
    
    private Date recordedAt;
    
    public Mood() {
        this.recordedAt = new Date();
    }

    public Mood(Date moodTimestamp) {
        this.recordedAt = moodTimestamp;
    }

    public Date getRecordedAt() {
        return this.recordedAt;
    }

    public void setRecordedAt(Date newTimestamp) {
        this.recordedAt = newTimestamp;
    }

    public abstract String describeMood();
}