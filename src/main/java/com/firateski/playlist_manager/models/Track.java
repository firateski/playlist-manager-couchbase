package com.firateski.playlist_manager.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Track {
    private String id;
    private String name;
    private int length;
    private String artist;
}
