package com.firateski.playlist_manager.services;

import com.firateski.playlist_manager.models.Playlist;
import com.firateski.playlist_manager.models.Track;

import java.util.List;

public interface PlaylistManager {

    void create(Playlist playlist);

    Playlist findById(String id);

    List<Playlist> findAllByUserId(String userId);

    void deletePlaylistById(String id);

    void addTrackToPlaylist(String id, Track track);

    void removeTrackFromPlaylist(String id, String trackId);
}
