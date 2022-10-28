package com.example.myyoutube.service;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VideoService {

    ///save video
    public Video saveVideo(Video video);

    ///All Videos
    Page<Video> getAllVideo();

    ///Find All Video By User Id
    List<Video> findAllVideoByUserId(Long id);

    ///Find Video All Information By Video Id
    public Video getVideoInfoById(Long id);

    ///Find Video Uploader Info
    public User findUserInfo(Long videoId);

}
