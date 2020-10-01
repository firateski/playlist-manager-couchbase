package com.firateski.playlist_manager.controllers;

import com.firateski.playlist_manager.models.Playlist;
import com.firateski.playlist_manager.models.Track;
import com.firateski.playlist_manager.services.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistManagerController {

    private final PlaylistService playlistService;

    public PlaylistManagerController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        playlistService.create(playlist);

        URI location = URI.create(String.format("/playlists/%s", playlist.getId()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> getPlaylistById(@PathVariable String id) {
        Playlist playlist = playlistService.findById(id);
        return ResponseEntity.ok(playlist);
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> getPlaylistsByUserId(@RequestParam String userId) {
        List<Playlist> playlists = playlistService.findAllByUserId(userId);
        return ResponseEntity.ok(playlists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Playlist> deletePlaylistById(@PathVariable String id) {
        playlistService.deletePlaylistById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<Void> addPTrackToPlaylist(@PathVariable String id, @RequestBody Track track) {
        playlistService.addTrackToPlaylist(id, track);

        URI location = URI.create(String.format("/playlists/%s/tracks/%s", id, track.getId()));
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}/tracks/{track_id}")
    public ResponseEntity<Void> addPTrackToPlaylist(@PathVariable String id, @PathVariable("track_id") String trackId) {
        playlistService.removeTrackFromPlaylist(id, trackId);

        return ResponseEntity.noContent().build();
    }

}
