package com.example.myyoutube.controller;


import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.UserService;
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

import static com.example.myyoutube.common.Constants.VDO_TEMPLATE;
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
        vdo.setVideoId(this.getVideoUniqueId(video.getVideoId())); ///Find video unique id
        vdo.setUploadedBy(user);              /////login user id diye kaj korte hobe
        vdo.setUploadedDate(this.getLocalDateTime());
        videoService.saveVideo(vdo);
        return "dashboard/index";
    }

    @GetMapping(value = "allvideos/{pageNumber}/{pageSize}/{sortDirection}",
            produces = APPLICATION_JSON_VALUE)
    private ResponseEntity<Page<Video>> findAllVideoByUserId(@PathVariable(value = "pageNumber", required = false) Integer pageNumber,
                                                             @PathVariable(value = "pageSize", required = false) Integer pageSize,
                                                             @PathVariable(value = "sortDirection", required = false) String sortDirection,
                                                             Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<Video> page = videoService.findAllVideoByUser(user, pageable);
        model.addAttribute("video", page);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "user-videos/{pageNumber}/{pageSize}/{sortDirection}")
    public ResponseEntity<Page<Video>> findAllVideos(
            @PathVariable(value = "pageNumber", required = false) Integer pageNumber,
            @PathVariable(value = "pageSize", required = false) Integer pageSize,
            @PathVariable(value = "sortDirection", required = false) String sortDirection) {

        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
        Page<Video> page = videoService.getAllVideo(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("videoinfo/{videoId}")
    public String getVideoInfo(@PathVariable("videoId") Long id, Model model) {
        Video video = videoService.getVideoInfoById(id);
        new Thread(() -> {
            videoService.addVideoView(id);
        }).start();
        video.setVideoLink(VDO_TEMPLATE.replace("${video_id}", video.getVideoId()));
        model.addAttribute("video", video);
        return "dashboard/video";
    }

    //////set like of video ///user er id lagbe tahole hobe ar aivabe dislike er kaj korte hobe
    @PostMapping("videolike/{videoId}")
    public String setLikeVideo(@PathVariable("videoId") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Video newVideoInfo = new Video();
        Video oldVideoInfo = videoService.getVideoInfoById(id);

        List<User> likedUsers = oldVideoInfo.getLikeBy();
        likedUsers.add(user);

        videoService.saveVideo(newVideoInfo);
        return "newVideoInfo";
    }

    ///Find Video Upload User Information
    @GetMapping("userinfo/{videoId}")
    public User getUserInf(@PathVariable("videoId") Long id) {
        return videoService.findUserInfo(id);
    }

    ///////// extra methods//////////

    private String getVideoUniqueId(String videoId) {
        return videoId.substring(videoId.lastIndexOf("/") + 1);
    }

    private LocalDateTime getLocalDateTime() {
        LocalDateTime dt = LocalDateTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        final DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE_TIME;
        return dt;
    }
}
