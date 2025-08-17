package com.example.news.data;

public class Article {
    private Source source;
    private String author;
    private String title;
    private String  description;
    private String url;
    private String urlToImage;
    private String  publishedAt;
    private String  content;

    public Article(String title, Source source,  String description, String urlToImage, String publishedAt, String content) {
        this.title = title;
        this.source = source;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }



    public Source getSource() {
        return source;
    }
    public String getAuthor() {
        return author;
    }



    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }


    public String getUrl() {
        return url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public String getContent() {
        return content;
    }
}
