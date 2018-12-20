package com.example.tstaats.myvermeer.Common;

public class TickerNews {

    private static final String TAG = "TickerNews";

    private String date;
    private String content;

    public TickerNews(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return  date + "\n" + content;
    }
}
