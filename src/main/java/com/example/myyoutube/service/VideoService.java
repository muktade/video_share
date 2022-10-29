package com.example.myyoutube.service;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface VideoService {

    ///save video
    Video saveVideo(Video video);

    ///All Videos
    Page<Video> getAllVideo(Pageable pageable);

    ///Find All Video By User Id
    List<Video> findAllVideoByUserId(Long id);

    ///Find Video All Information By Video Id
    Video getVideoInfoById(Long id);

    ///Find Video Uploader Info
    User findUserInfo(Long videoId);

    boolean addVideoView(long videoId);



}
