package com.example.myyoutube.repository;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Modifying
    @Query("UPDATE Video v SET v.totalViews = v.totalViews + 1 where v.id = ?1")
    int addVideoView(long videoId);

//    @Query("SELECT v.* FROM Video v WHERE v.uploadedBy = ?1")
    List<Video> findAllVideoByUploadedBy(User user);

    Page<Video> findByUploadedById(long userId, Pageable pageable);

//    @Query("select count(v) from Video v where v. = ?1")
//    int getLikeById(Long videoId);
}
