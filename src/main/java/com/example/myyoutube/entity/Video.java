package com.example.myyoutube.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "video",
        indexes = {
                @Index(name = "idx_video_id", columnList = "video_id")
        })
public class Video extends BaseEntity {

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "uploaded_by", referencedColumnName = "id")
    private User uploadedBy;

   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime uploadedDate;

    @Column(name = "total_views")
    private long totalViews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "like_by", referencedColumnName = "id")
    private List<User> likeBy;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "dislike_by", referencedColumnName = "id")
    private List<User> dislikeBy;

    @Transient
    private String videoLink;

    @Transient
    private String videoThumbnail;

    @Transient
    private int videoLike;

    @Transient
    private int videoDislike;

    @Transient
    private String date;

}
