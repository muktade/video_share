package com.example.myyoutube.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @Column(name = "description")
    private String description;

    @Column(name = "total_views")
    private Long totalViews;
}
