package com.techtalent.playlist.repository;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.techtalent.playlist.exception.PlayListNotFoundException;
import org.springframework.stereotype.Repository;
import com.techtalent.playlist.domain.PlayList;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayListRepository {
    private final Cluster couchbaseCluster;
    private final Collection playListCollection;

    public PlayListRepository(Cluster couchbaseCluster, Collection playListCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playListCollection = playListCollection;
    }

    public void insert(PlayList playlist) {
        playListCollection.insert(playlist.getId(), playlist);
    }

    public void update(PlayList playlist) {
        playListCollection.replace(playlist.getId(), playlist);
    }

    public void remove(String Id) {
        playListCollection.remove(Id);
    }

    public PlayList findById(String id) {
        try {
            GetResult getResult = playListCollection.get(id);
            PlayList playList = getResult.contentAs(PlayList.class);
            return Optional.of(playList).get();

        } catch (DocumentNotFoundException exception) {
           throw  new PlayListNotFoundException(id);
        }
    }

    public List<PlayList> findAll() {
        String statement = "Select id, name,description,tracks,userId, trackCount, followersCount  from playList";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(PlayList.class);
    }

    public List<PlayList> findByUserId(String userId) {
        String statement = " SELECT name,description,id,tracks,followersCount,trackCount FROM `playList` WHERE userId =" + "'" + userId + "'";
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(PlayList.class);
    }

}