package com.techtalent.playlist;

import com.techtalent.playlist.domain.PlayList;
import com.techtalent.playlist.exception.PlayListNotFoundException;
import com.techtalent.playlist.repository.PlayListRepository;
import com.techtalent.playlist.service.PlayListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayListServiceTests {


    @Mock
    private PlayListRepository playListRepository;

    @InjectMocks
    private PlayListService playListService;

    private List<PlayList> playLists;

    @Before
    public void setup() {
        this.playLists = new ArrayList<>();
        addDummyPlayLists();

    }


    @Test
    public void findAllByUserId_should_return_all_playlist_for_given_userId() {
        //Arrange
        String userId = "3321";
        when(playListRepository.findByUserId(userId)).thenReturn(this.playLists);

        //Act
        List<PlayList> playLists = playListService.findAllByUserId(userId);

        //Assertion
        assertThat(playLists.stream().count()).isEqualTo(10);

    }

    @Test
    public void getByPlayListId_should_return_playList_for_given_playListId() {
        //Arrange
        PlayList fakePlayList = new PlayList();
        when(playListRepository.findById(fakePlayList.getId())).thenReturn(fakePlayList);
        //Act
        PlayList playList = playListService.getByPlayListId(fakePlayList.getId());

        //Assert
        assertThat(playList).isEqualTo(fakePlayList);
    }

    @Test
    public void getByPlayListId_should_throw_PlayListNotFoundException_when_given_playlist_not_found() {
        // Arrange
        String playListId = "1fa7ded3-6ce7-4a32-8ba0-1620e2a32ce4";
        when(playListRepository.findById(playListId)).thenThrow(PlayListNotFoundException.class);
        //Act
        Throwable throwable = catchThrowable(() -> playListService.getByPlayListId(playListId));
        //Assert
        assertThat(throwable).isInstanceOf(PlayListNotFoundException.class);

    }

    @Test
    public void delete_should_throw_PlayListNotFoundException_when_given_playlist_not_found(){
        // Arrange
        String playListId = "1fa7ded3-6ce7-4a32-8ba0-1620e2a32ce4";
        when(playListRepository.findById(playListId)).thenThrow(PlayListNotFoundException.class);
        //Act
        Throwable throwable = catchThrowable(() -> playListService.delete(playListId));
        //Assert
        assertThat(throwable).isInstanceOf(PlayListNotFoundException.class);
    }




    private void addDummyPlayLists() {
        for (int i = 0; i < 10; i++) {
            this.playLists.add(new PlayList());
        }
    }

}
