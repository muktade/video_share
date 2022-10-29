package com.example.myyoutube.common;

import com.example.myyoutube.entity.Video;

public class Constants {
    public static final String  VDO_TEMPLATE ="<iframe width=\"853\" height=\"480\" src=\"https://www.youtube.com/embed/${video_id}\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

    public static final String VDO_SHARE_TEMPLATE = "https://youtu.be/";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ERROR = "error";
    public static final String KEY_USER = "user";

//    System.out.println("https://youtu.be/tiFKml1IKII".substring("https://youtu.be/tiFKml1IKII".lastIndexOf("/") + 1));
}
