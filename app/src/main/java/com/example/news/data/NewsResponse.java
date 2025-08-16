package com.example.news.data;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<HeadLine> articles; // <-- list of headlines

    public String getStatus() { return status; }
    public int getTotalResults() { return totalResults; }
    public List<HeadLine> getArticles() { return articles; }
}
