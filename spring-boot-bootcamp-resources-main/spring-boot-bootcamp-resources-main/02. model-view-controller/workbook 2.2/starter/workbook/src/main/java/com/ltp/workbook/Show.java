package com.ltp.workbook;

public class Show {
    private String title;
    private String episode;
    private String rating;

    public Show(String title, String episode, String rating) {
        this.title = title;
        this.episode = episode;
        this.rating = rating;
    }

    public Show(){}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode() {
        return this.episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    
}
