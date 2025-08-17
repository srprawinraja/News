package com.example.news;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.adapter.NewsAdapter;
import com.example.news.data.Article;
import com.example.news.data.Source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button prevClicked;
    private NewsAdapter adapter;
    private EditText editText;
    private final String COUNTRY = "us";
    private NewsViewModel  newsViewModel;
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
        recyclerView = findViewById(R.id.newsRecyclerView);
        editText = findViewById(R.id.search_input);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false){

        });


        // set adapter
        adapter = new NewsAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);


        // category
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        LinearLayout categoryContainer = findViewById(R.id.categoryContainer);
        newsViewModel.getAllHeadLinesLiveData().observe(this, response -> {

            // Update UI with users
            if(response!=null){
                adapter.updateData(response.getArticles());
            } else {
               // handle error
            }
        });


        // buttons
        List<Button> buttons = new ArrayList<>();
        String[] categories = {"Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"};
        newsViewModel.fetchHeadLines(COUNTRY, categories[0].toLowerCase(), this);

        ContextThemeWrapper styledContext = new ContextThemeWrapper(this, R.style.Theme_News);
        for (int i=0; i<categories.length; i++) {
            String category = categories[i];

            Button btn = getButtons(styledContext, category, i);
            buttons.add(btn);
            if(i==0){
                prevClicked=btn;
            }
            categoryContainer.addView(btn);
        }

        // search
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();

                for (Button btn : buttons) {
                    String text = btn.getText().toString().toLowerCase();
                    if (query.isEmpty() || text.contains(query)) {
                        btn.setVisibility(View.VISIBLE);
                    } else {
                        btn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private Button getButtons(ContextThemeWrapper styledContext, String category, int index){
        Button btn = new Button(styledContext);
        btn.setTextAppearance(R.style.CategorySearchText);
        btn.setText(category);
        btn.setBackground(null);
        btn.setAllCaps(false);
        if(index==0){
            btn.setTextColor(ContextCompat.getColor(this, R.color.littleDark));
        }
        btn.setOnClickListener(v -> {
            if(prevClicked!=null){
                prevClicked.setTextColor(ContextCompat.getColor(this, R.color.cementOnBackground));
            }

            newsViewModel.fetchHeadLines(COUNTRY, category.toLowerCase(), this);

            btn.setTextColor(ContextCompat.getColor(this, R.color.littleDark));
            prevClicked = btn;

        });
        return btn;
    }


}