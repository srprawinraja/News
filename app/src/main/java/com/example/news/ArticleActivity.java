package com.example.news;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ArticleActivity extends AppCompatActivity {
    private TextView contentView, titleView, categoryView;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article);

        titleView = findViewById(R.id.title);
        contentView = findViewById(R.id.articleContent);
        categoryView = findViewById(R.id.category);
        img = findViewById(R.id.imageView);

        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String imageUrl = getIntent().getStringExtra("img");
        String category = getIntent().getStringExtra("category");

        contentView.setText(content);
        titleView.setText(title);
        categoryView.setText(category);
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholdercard)
                .error(R.drawable.img)
                .into(img);

    }
}