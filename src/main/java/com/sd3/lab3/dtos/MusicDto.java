package com.sd3.lab3.dtos;


public class MusicDto {
    public String title;
    public String singer;
    public String genre;
    public double duration;
    public String link;

    public MusicDto() {
    }

    public MusicDto(String title, String singer, String genre, double duration, String link) {
        this.title = title;
        this.singer = singer;
        this.genre = genre;
        this.duration = duration;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "MusicDto{" +
                "title='" + title + '\'' +
                ", singer='" + singer + '\'' +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", link='" + link + '\'' +
                '}';
    }
}
