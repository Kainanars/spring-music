package com.sd3.lab3.entities;

import jakarta.persistence.*;

@Entity
@Table(name="TB_MUSICS")
public class Music extends AbstractEntity {

    @Column(name = "NM_TITLE")
    private String title;

    @Column(name = "NM_SINGER")
    private String singer;

    @Column(name = "NM_GENRE")
    private String genre;

    @Column(name = "NR_DURATION")
    private double duration;

    @Column(name = "IX_LINK")
    private String link;

    public Music() {
    }

    public Music(String title, String singer, String genre, double duration, String link) {
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
}
