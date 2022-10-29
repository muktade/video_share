package com.example.myyoutube.controller;

import com.example.myyoutube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
