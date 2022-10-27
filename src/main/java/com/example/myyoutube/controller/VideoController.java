package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.UserService;
import com.example.myyoutube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video/")
public class VideoController {

    private final VideoService videoService;
    private final UserService userService;

    ////add Video
    @PostMapping("save")
    public Video addVideo(@RequestBody Video video){
        return videoService.saveVideo(video);
    }

    ///Find All Video
    @GetMapping("allvideos")
    private List<Video> findAllVideos(){
        return videoService.getAllVideo();
    }

    ///Find All Videos By User
    @GetMapping("userallvideos/{userId}") ///user Id = Login User Id
    public List<Video> findAllVideoByUserId(@PathVariable("userId") Long id){
        return videoService.findAllVideoByUserId(id);
    }

    ///find Video Info By Id
    @GetMapping("videoinfo/{videoId}")
    public Video getVideoInfo(@PathVariable("videoId") Long id){
        return videoService.getVideoInfoById(id);
    }

    ///Find Video Upload User Information
    @GetMapping("userinfo/{videoId}")
    public User getUserInf(@PathVariable("videoId") Long id){
        return videoService.findUserInfo(id);
    }

}
