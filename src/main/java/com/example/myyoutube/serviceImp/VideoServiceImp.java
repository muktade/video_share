package com.example.myyoutube.serviceImp;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.repository.UserRepository;
import com.example.myyoutube.repository.VideoRepository;
import com.example.myyoutube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoServiceImp implements VideoService{

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideo() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> findAllVideoByUserId(Long id) {
        return (List<Video>) videoRepository.findById(id).orElse(new Video());
    }

    @Override
    public Video getVideoInfoById(Long id) {
        return videoRepository.findById(id).orElse(new Video());
    }

    @Override
    public User findUserInfo(Long videoId) {
        return userRepository.findById(videoId).orElse(new User());
    }
}
