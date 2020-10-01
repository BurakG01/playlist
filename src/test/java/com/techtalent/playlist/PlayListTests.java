package com.techtalent.playlist;

import com.techtalent.playlist.domain.PlayList;
import com.techtalent.playlist.domain.Track;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayListTests {

    @Test
    public void should_create_playList() {
        // Arrange
        PlayList playList = new PlayList();

        // Act
        int trackCount = playList.getTrackCount();
        int followersCount = playList.getFollowersCount();
        String playListId = playList.getId();

        //Assert
        assertThat(trackCount).isEqualTo(0);
        assertThat(followersCount).isEqualTo(0);
        assertThat(playListId).isNotEmpty();
    }

    @Test
    public void addTrack_should_add_track() {
        // Arrange
        PlayList playList = new PlayList();


        //Act
        playList.addTrack(new Track());
        List<Track> tracks = playList.getTracks();
        //Assert

        assertThat(tracks.stream().count()).isEqualTo(1);

    }

    @Test
    public void removeTrack_should_remove_track() {
        // Arrange
        PlayList playList = new PlayList();
        Track track = new Track();
        track.setName("Come on");

        //Act
        playList.addTrack(track);

        playList.removeTrack(track.getName());
        List<Track> tracks = playList.getTracks();

        //Assert
        assertThat(tracks.stream().count()).isEqualTo(0);

    }



}
