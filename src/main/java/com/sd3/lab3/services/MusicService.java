package com.sd3.lab3.services;

import com.sd3.lab3.dtos.MusicDto;
import com.sd3.lab3.entities.Music;
import com.sd3.lab3.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public List<Music> getAllMusic() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Long id) {
        return musicRepository.findById(id).orElse(null);
    }

    public List<Music> getMusics(String title, String singer, String genre) {
        if (title != null) title = title.toLowerCase();
        if (singer != null) singer = singer.toLowerCase();
        if (genre != null) genre = genre.toLowerCase();
        List<Music> musics =  musicRepository.findMusics(title, singer, genre);
        System.out.println(musics);
        return musics;
    }


    public Music createMusic(MusicDto musicDto) {
        Music music = new Music();
        music.setTitle(musicDto.getTitle());
        music.setSinger(musicDto.getSinger());
        music.setGenre(musicDto.getGenre());
        music.setDuration(musicDto.getDuration());
        music.setLink(musicDto.getLink());
        return musicRepository.save(music);
    }


    public Music updateMusic(Long id, MusicDto musicDto) {
        Music music = musicRepository.findById(id).orElse(null);
        if (music == null) {
            return null;
        }
        music.setTitle(musicDto.getTitle());
        music.setSinger(musicDto.getSinger());
        music.setGenre(musicDto.getGenre());
        music.setDuration(musicDto.getDuration());
        music.setLink(musicDto.getLink());
        return musicRepository.save(music);
    }

    public boolean deleteMusic(Long id) {
        Music music = musicRepository.findById(id).orElse(null);
        if (music == null) {
            return false;
        }
        musicRepository.delete(music);
        return true;
    }
}
