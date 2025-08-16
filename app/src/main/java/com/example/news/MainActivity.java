package com.example.news;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NewsModel newsModel = new NewsModel();
        String[] categories = {"business", "entertainment", "general", "health", "science", "sports", "technology"};
        ContextThemeWrapper styledContext = new ContextThemeWrapper(this, R.style.Theme_News);

        LinearLayout categoryContainer = findViewById(R.id.categoryContainer);

        for (String category : categories) {
            Button btn = new Button( styledContext);
            btn.setTextAppearance(R.style.CategorySearchText);
            btn.setText(category);
            btn.setBackground(null);
            btn.setOnClickListener(v -> {
                Log.i("button got clicked", "button got clicked");
                newsModel.getAllHeadLines();
                newsModel.allHeadLinesLiveData.observe(this, response -> {
                    // Update UI with users
                    Log.i("button got clicked", response.getStatus());
                });

                newsModel.allHeadLinesErrorLiveData.observe(this, error -> {
                    // Show error message if needed
                    Log.i("button got clicked", error);
                });

            });

            categoryContainer.addView(btn);
        }


    }
}