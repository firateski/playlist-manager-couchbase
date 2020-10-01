package com.firateski.playlist_manager.services;

import com.firateski.playlist_manager.models.Playlist;
import com.firateski.playlist_manager.models.Track;
import com.firateski.playlist_manager.repositories.PlaylistRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class PlaylistManagerService implements PlaylistManager {

    private final PlaylistRepository playlistRepository;

    public PlaylistManagerService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void create(Playlist playlist) {
        playlistRepository.createPlaylist(playlist);
    }

    public Playlist findById(String id){
        return playlistRepository.findById(id);
    }

    public List<Playlist> findAllByUserId(String userId){
        return playlistRepository.findAllByUserId(userId);
    }

    public void deletePlaylistById(String id){
        playlistRepository.deletePlaylistById(id);
    }

    public void addTrackToPlaylist(String id, Track track){
        playlistRepository.addTrackToPlayList(id, track);
    }

    public void removeTrackFromPlaylist(String id, String trackId){
        playlistRepository.removeTrackFromPlaylist(id, trackId);
    }
}
