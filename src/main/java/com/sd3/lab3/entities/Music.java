package com.sd3.lab3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_MUSICS")
@Getter
@Setter
public class Music extends AbstractEntity {

    //título, artista, gênero e duração da música.
    @Column(name = "NM_TITLE")
    private String Title;

    @Column(name = "NM_SINGER")
    private String Singer;

    @Column(name = "NM_GENRE")
    private String Genre;

    @Column(name = "NR_DURATION")
    private double Duration;

    @Column(name = "IX_LINK", nullable = true)
    private String Link;

    public Music(String title, String singer, String genre, double duration, String link) {
        Title = title;
        Singer = singer;
        Genre = genre;
        Duration = duration;
        Link = link;
    }

    public Music() {

    }
}
