package com.sd3.lab3.repositories;

import com.sd3.lab3.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    @Query("SELECT m FROM Music m WHERE " +
            "(:title IS NULL OR LOWER(m.Title) LIKE %:title%) AND " +
            "(:singer IS NULL OR LOWER(m.Singer) LIKE %:singer%) AND " +
            "(:genre IS NULL OR LOWER(m.Genre) LIKE %:genre%)")
    List<Music> findMusics(@Param("title") String title, @Param("singer") String singer, @Param("genre") String genre);


}
