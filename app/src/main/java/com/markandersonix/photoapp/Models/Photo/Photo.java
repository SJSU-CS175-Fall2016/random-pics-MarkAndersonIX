package com.markandersonix.photoapp.Models.Photo;

/**
 * Created by Mark on 10/8/2016.
 */

public class Photo {
    private String thumb;
    private String large;
    private String id;
    private String author;
    private String username;

    public Photo(String id, String author, String username, String thumb, String large) {
        this.id = id;
        this.author = author;
        this.username = username;
        this.thumb = thumb;
        this.large = large;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSmall() {
        return thumb;
    }

    public void setSmall(String small) {
        this.thumb = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getUrl() {
        return id;
    }

    public void setUrl(String url) {
        this.id = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}