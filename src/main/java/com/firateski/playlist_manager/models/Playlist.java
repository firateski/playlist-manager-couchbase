package com.firateski.playlist_manager.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Playlist {
    private String id;
    private String name;
    private String description;
    private int followersCount;
    private List<Track> tracks;
    private int trackCount;
    private String userId;

}
