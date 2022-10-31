package com.example.myyoutube.service;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VideoService {

    Video saveVideo(Video video);

    Page<Video> findAllVideoByUser(User user, Pageable pageable);

    Video getVideoInfoById(Long id);

    User findUserInfo(Long videoId);

    boolean addVideoView(long videoId);

    boolean likeVideo(long videoId, long userId);

    boolean dislikeVideo(long videoId, long userId);
}
