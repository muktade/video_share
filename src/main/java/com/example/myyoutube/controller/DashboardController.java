package com.example.myyoutube.controller;

import com.example.myyoutube.entity.Video;
import com.example.myyoutube.service.VideoService;
import com.example.myyoutube.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard/")
public class DashboardController {

    private final VideoService videoService;

//    @GetMapping(value = {"dashboard","dashboard/{pageNumber}/{pageSize}/{sortDirection}"},
//            produces = APPLICATION_JSON_VALUE)
//    private String findAllVideos(@PathVariable(value = "pageNumber",required = false) Integer pageNumber,
//                                                      @PathVariable(value = "pageSize", required = false) Integer pageSize,
//                                                      @PathVariable(value = "sortDirection", required = false) String sortDirection, Model model){
//        Pageable pageable = PageUtils.getPageable(pageNumber, pageSize, sortDirection, "id");
//        Page<Video> page = videoService.getAllVideo(pageable);
//        model.addAttribute("video", page);
//        return "layout/dashboard";
//    }
}
