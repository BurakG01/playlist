package com.techtalent.playlist.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayList {
    private String id;
    private String userId;
    private String name;
    private String description;
    private int followersCount;
    private int trackCount;
    private List<Track> tracks;


    public PlayList() {
        this.id = UUID.randomUUID().toString();
        this.tracks = new ArrayList<>();
        this.trackCount = 0;
        this.followersCount = 0;
    }

    public void increaseTrackCount() {
        this.trackCount++;
    }

    public void decreaseTrackCount() {
        this.trackCount--;
    }

    public String getId() {
        return id;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
    }

    public void removeTrack(String trackName) {
        this.tracks.removeIf(t -> t.getName().equals(trackName));
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Track> getTracks() {
        return tracks;
    }

}
