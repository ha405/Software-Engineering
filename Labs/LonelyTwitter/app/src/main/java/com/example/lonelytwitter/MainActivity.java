package com.example.lonelytwitter;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImportantTweet priorityTweet = new ImportantTweet("This tweet has high priority");
        NormalTweet regularTweet = new NormalTweet("Just another regular tweet here");

        ArrayList<Tweet> allTweets = new ArrayList<Tweet>();

        allTweets.add(priorityTweet);
        allTweets.add(regularTweet);

        HappyMood joyfulFeeling = new HappyMood();
        SadMood gloomyFeeling = new SadMood();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, windowInsets) -> {
            Insets barInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(barInsets.left, barInsets.top, barInsets.right, barInsets.bottom);
            return windowInsets;
        });
    }
}