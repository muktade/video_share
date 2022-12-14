package com.example.myyoutube.serviceImp;

import com.example.myyoutube.entity.User;
import com.example.myyoutube.entity.Video;
import com.example.myyoutube.repository.UserRepository;
import com.example.myyoutube.repository.VideoRepository;
import com.example.myyoutube.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VideoServiceImp implements VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Page<Video> findAllVideoByUser(User user, Pageable pageable) {
        Page<Video> page;
        if (user == null) {
            page = videoRepository.findAll(pageable);
        } else {
            page = videoRepository.findByUploadedById(user.getId(), pageable);
        }
        return page;
    }

    @Override
    public Video getVideoInfoById(Long id) {
        return videoRepository.findById(id).orElse(new Video());
    }

    @Override
    public User findUserInfo(Long videoId) {
        return userRepository.findById(videoId).orElse(new User());
    }

    @Override
    public boolean addVideoView(long videoId) {
        int result = videoRepository.addVideoView(videoId);
        return result > 0;
    }

    @Override
    public boolean likeVideo(long videoId, long userId) {
        int userLikeCount = videoRepository.isLiked(videoId, userId);
        if (userLikeCount == 0) {
            videoRepository.deleteDislikeVideo(videoId, userId);
            int result = videoRepository.likeVideo(videoId, userId);
            return result > 0;
        }
        return false;
    }

    @Override
    public boolean dislikeVideo(long videoId, long userId) {
        int userLikeCount = videoRepository.isDisliked(videoId, userId);
        if (userLikeCount == 0) {
            videoRepository.deleteLikeVideo(videoId, userId);
            int result = videoRepository.dislikeVideo(videoId, userId);
            return result > 0;
        }
        return false;
    }

}
