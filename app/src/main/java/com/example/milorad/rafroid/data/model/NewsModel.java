package com.example.milorad.rafroid.data.model;

public class NewsModel {

    private String title;
    private String text;
    private String date;

    public NewsModel(String title, String text, String date){
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }
}
