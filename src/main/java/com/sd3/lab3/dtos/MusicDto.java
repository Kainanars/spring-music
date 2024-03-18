package com.sd3.lab3.dtos;


public class MusicDto {
    public String Title;
    public String Singer;
    public String Genre;
    public double Duration;
    public String Link;


    public MusicDto() {
    }

    public MusicDto(String title, String singer, String genre, double duration, String link) {
        Title = title;
        Singer = singer;
        Genre = genre;
        Duration = duration;
        Link = link;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public double getDuration() {
        return Duration;
    }

    public void setDuration(double duration) {
        Duration = duration;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
