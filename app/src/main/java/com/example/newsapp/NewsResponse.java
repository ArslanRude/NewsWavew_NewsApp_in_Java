package com.example.newsapp;

import com.kwabenaberko.newsapilib.models.Article;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
