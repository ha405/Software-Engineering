package com.example.lonelytwitter;

import java.util.Date;

public interface Tweetable {
    
    String fetchText();
    
    Date fetchCreatedOn();
    
    Boolean hasPriority();
}