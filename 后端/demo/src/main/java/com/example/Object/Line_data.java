package com.example.Object;

public class Line_data {
    private int ratingUpdateTimeSeconds,Rating;
    private String Realname;

    public Line_data(String realname,int ratingUpdateTimeSeconds, int rating) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
        Rating = rating;
        Realname = realname;
    }

    public int getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(int ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getRealname() {
        return Realname;
    }

    public void setRealname(String realname) {
        Realname = realname;
    }
}
