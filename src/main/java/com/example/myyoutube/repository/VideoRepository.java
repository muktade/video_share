package com.example.myyoutube.repository;

import com.example.myyoutube.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("UPDATE Video v SET v.totalViews = v.totalViews + 1 where v.id = ?1")
    int addVideoView(long videoId);

}
