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

    @Modifying
    @Query(value = "insert into video_like_by values(?1, ?2)", nativeQuery = true)
    int likeVideo(long videoId, long userId);

    @Modifying
    @Query(value = "insert into video_dislike_by values(?1, ?2)", nativeQuery = true)
    int dislikeVideo(long videoId, long userId);

    @Modifying
    @Query(value = "delete from video_like_by where video_id = ?1 and like_by_id = ?2", nativeQuery = true)
    int deleteLikeVideo(long videoId, long userId);

    @Modifying
    @Query(value = "delete from video_dislike_by where video_id = ?1 and dislike_by_id = ?2", nativeQuery = true)
    int deleteDislikeVideo(long videoId, long userId);

    @Query(value = "select count(video_id) from video_dislike_by where video_id = ?1 and dislike_by_id = ?2", nativeQuery = true)
    int isDisliked(long videoId, long userId);

    @Query(value = "select count(video_id) from video_like_by where video_id = ?1 and like_by_id = ?2", nativeQuery = true)
    int isLiked(long videoId, long userId);

    List<Video> findAllVideoByUploadedBy(User user);

    Page<Video> findByUploadedById(long userId, Pageable pageable);

}
