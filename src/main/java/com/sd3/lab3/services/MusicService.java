package com.sd3.lab3.services;

import com.sd3.lab3.dtos.MusicDto;
import com.sd3.lab3.entities.Music;
import com.sd3.lab3.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        Music music = new Music(musicDto.title, musicDto.singer, musicDto.genre, musicDto.duration, musicDto.link );
        return musicRepository.save(music);
    }

    public Music updateMusic(Long id, MusicDto musicDto) {
        Music existingMusic = musicRepository.findById(id).orElse(null);
        System.out.println(musicDto.toString());
        if (existingMusic != null) {
            existingMusic.setTitle(musicDto.getTitle());
            existingMusic.setSinger(musicDto.getSinger());
            existingMusic.setGenre(musicDto.getGenre());
            existingMusic.setDuration(musicDto.getDuration());
            existingMusic.setLink(musicDto.getLink());
            existingMusic.setUpdatedAt(new Date());
            return musicRepository.save(existingMusic);
        }
        return null;
    }

    public Music patchMusic(Long id, Map<String, Object> updates) {
        Music music = musicRepository.findById(id).orElse(null);
        assert music != null;

        updates.forEach((key, value) -> {
            switch (key) {
                case "title" -> music.setTitle((String) value);
                case "singer" -> music.setSinger((String) value);
                case "genre" -> music.setGenre((String) value);
                case "duration" -> music.setDuration((double) value);
                case "link" -> music.setLink((String) value);
            }
        });
        music.setUpdatedAt(new Date());
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
