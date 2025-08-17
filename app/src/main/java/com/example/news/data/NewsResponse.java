package com.example.news.data;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;
    public List<Article> getArticles() {
        return articles;
    }
    public int getTotalResults() {
        return totalResults;
    }
    public String getStatus() {
        return status;
    }
}
