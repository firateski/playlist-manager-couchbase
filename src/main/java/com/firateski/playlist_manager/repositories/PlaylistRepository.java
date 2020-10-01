package com.firateski.playlist_manager.repositories;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.firateski.playlist_manager.models.Playlist;
import com.firateski.playlist_manager.models.Track;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void createPlaylist(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
    }

    public Playlist findById(String id) {
        GetResult result = playlistCollection.get(id);
        return result.contentAs(Playlist.class);
    }

    public List<Playlist> findAllByUserId(String userId){
        String statement = "Select " +
                        "id, name, description, followersCount, tracks, trackCount, userId " +
                        "from playlist " +
                        "where userId=$1";

        QueryResult query = couchbaseCluster.query(statement, queryOptions().parameters(JsonArray.from(userId)));
        return query.rowsAs(Playlist.class);
    }

    public void deletePlaylistById(String id){
        playlistCollection.remove(id);
    }

    public void addTrackToPlayList(String id, Track track){
        Playlist playlist = findById(id);

        if(Objects.isNull(playlist.getTracks())){
            playlist.setTracks(new ArrayList<>());
        }

        playlist.getTracks().add(track);
        playlistCollection.replace(id, playlist);
    }

    public void removeTrackFromPlaylist(String id, String trackId){
        Playlist playlist = findById(id);

        playlist.getTracks().removeIf(x -> x.getId().equals(trackId));
        playlistCollection.replace(id, playlist);
    }
}
