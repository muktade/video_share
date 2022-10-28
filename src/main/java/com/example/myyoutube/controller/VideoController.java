package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.UserService;
import com.example.myyoutube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video/")
public class VideoController {

    private final VideoService videoService;
    private final UserService userService;

    ////add Video
    @PostMapping(value = "save",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
    public Video addVideo(@RequestBody Video video){

        Video vdo = new Video();
        vdo.setTitle(video.getTitle());
        vdo.setVideoId(this.getVideoUniqueId(video.getVideoId())); ///video unique id
        vdo.setUploadedBy(new User());              /////login user id diye kaj korte hobe
        vdo.setUploadedDate(this.getLocalDateTime());

        return videoService.saveVideo(vdo);
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

    ///find Video Info By Id and increase total views
    @GetMapping("videoinfo/{videoId}")
    public Video getVideoInfo(@PathVariable("videoId") Long id){
        Video newVideoInfo = new Video();
        Video oldVideoInfo = videoService.getVideoInfoById(id);
        if(oldVideoInfo.getTotalViews()==null){
            newVideoInfo.setTotalViews(Long.parseLong("1"));
        }else {
            newVideoInfo.setTotalViews(oldVideoInfo.getTotalViews()+1);
        }
        videoService.saveVideo(newVideoInfo);
        return newVideoInfo;
    }

    //////set like of video ///user er id lagbe tahole hobe ar aivabe dislike er kaj korte hobe
    @PostMapping("videolike/{videoId}")
    public String setLikeVideo(@PathVariable("videoId") Long id){
        Video newVideoInfo = new Video();
        Video oldVideoInfo = videoService.getVideoInfoById(id);  /////Login User er id lagbe
        if(oldVideoInfo.getTotalViews()==null){
            newVideoInfo.setTotalViews(Long.parseLong("1"));
        }else {
            newVideoInfo.setTotalViews(oldVideoInfo.getTotalViews()+1);
        }
        videoService.saveVideo(newVideoInfo);
        return "newVideoInfo";
    }



    ///Find Video Upload User Information
    @GetMapping("userinfo/{videoId}")
    public User getUserInf(@PathVariable("videoId") Long id){
        return videoService.findUserInfo(id);
    }


    ///////// extra methods

    private String getVideoUniqueId(String videoId) {
        return videoId.substring(videoId.lastIndexOf("/"+1));
    }

    private LocalDateTime getLocalDateTime() {
        LocalDateTime dt = LocalDateTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        final DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
        return dt;
    }
}
