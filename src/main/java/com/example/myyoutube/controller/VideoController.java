package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.VideoService;
import com.example.myyoutube.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/video/")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("upload")
    public String addUserPage(Model model) {
        model.addAttribute("video", new Video());
        return "dashboard/add_video";
    }

    @PostMapping(value = "save")
    public String addVideo(Video video, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Video vdo = new Video();
        vdo.setTitle(video.getTitle());
        vdo.setVideoId(this.getVideoUniqueId(video.getVideoId()));
        vdo.setUploadedBy(user);
        vdo.setUploadedDate(LocalDateTime.now());
        videoService.saveVideo(vdo);
        return "dashboard/index";
    }

    @GetMapping(value = "allvideos/{pageNumber}/{pageSize}", produces = APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Video>> findAllVideoByUserId(@PathVariable(value = "pageNumber", required = false) Integer pageNumber,
                                                             @PathVariable(value = "pageSize", required = false) Integer pageSize) {
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, ASC, "id");
        Page<Video> page = videoService.findAllVideoByUser(null, pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping(value = {"user-videos", "user-videos/{pageNumber}/{pageSize}"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Video>> findAllVideos(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            HttpSession session) {
        User user = (User) session.getAttribute("user");
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, ASC, "id");
        Page<Video> page = videoService.findAllVideoByUser(user, pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("videoinfo/{videoId}")
    public String getVideoInfo(@PathVariable("videoId") Long id, Model model) {
        Video video = videoService.getVideoInfoById(id);
        new Thread(() -> {
            videoService.addVideoView(id);
        }).start();
        model.addAttribute("video", video);
        return "dashboard/video_details";
    }

    @GetMapping("videolike/{videoId}")
    public String setLikeVideo(@PathVariable("videoId") Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Video oldVideoInfo = videoService.getVideoInfoById(id);
        List<User> likedUsers = oldVideoInfo.getLikeBy();
        for(User usr : oldVideoInfo.getLikeBy()){
            if(usr.getId() == user.getId()){
                return "redirect:/video/videoinfo/"+id;
            }
        }
        likedUsers.add(user);
        oldVideoInfo.setLikeBy(likedUsers);
        videoService.saveVideo(oldVideoInfo);
        model.addAttribute("likeUserList", likedUsers);
        return "redirect:/video/videoinfo/"+id;

    }

    @GetMapping("videodislike/{videoId}")
    public String setDislikeVideo(@PathVariable("videoId") Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Video videoInfo = videoService.getVideoInfoById(id);
        for(User usr : videoInfo.getDislikeBy()){
            if(usr.getId() == user.getId()){
                return "redirect:/video/videoinfo/"+id;
            }
        }
        System.out.println(videoInfo.getLikeBy());

        List<User> dislikeUsers = videoInfo.getDislikeBy();
        dislikeUsers.add(user);

        videoInfo.setDislikeBy(dislikeUsers);

        videoService.saveVideo(videoInfo);
        model.addAttribute("dislikeUserList", dislikeUsers);
        return "redirect:/video/videoinfo/"+id;
    }

    @GetMapping("userinfo/{videoId}")
    public User getUserInf(@PathVariable("videoId") Long id) {
        return videoService.findUserInfo(id);
    }

    private String getVideoUniqueId(String videoId) {
        return videoId.substring(videoId.lastIndexOf("/") + 1);
    }

}
