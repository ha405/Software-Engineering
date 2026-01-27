package com.example.lonelytwitter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Tweet> tweetCollection;
    private List<Mood> moodHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        initializeData();
        configureWindowInsets();
    }
    
    private void initializeData() {
        tweetCollection = new ArrayList<>();
        moodHistory = new ArrayList<>();
        
        Tweet highPriorityPost = new ImportantTweet("An urgent announcement");
        Tweet regularPost = new NormalTweet("Just sharing my thoughts");
        
        tweetCollection.add(highPriorityPost);
        tweetCollection.add(regularPost);
        
        Mood cheerfulState = new HappyMood();
        Mood downState = new SadMood();
        
        moodHistory.add(cheerfulState);
        moodHistory.add(downState);
    }
    
    private void configureWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (targetView, insetsData) -> {
            Insets bars = insetsData.getInsets(WindowInsetsCompat.Type.systemBars());
            targetView.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insetsData;
        });
    }
}