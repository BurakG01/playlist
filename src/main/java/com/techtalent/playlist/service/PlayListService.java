package com.techtalent.playlist.service;

import com.techtalent.playlist.contract.request.CreatePlayList;
import com.techtalent.playlist.domain.PlayList;
import com.techtalent.playlist.domain.Track;
import com.techtalent.playlist.repository.PlayListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayListService {
    private final PlayListRepository playListRepository;


    public PlayListService(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    public String create(CreatePlayList createPlayList) {
        PlayList playList = new PlayList();
        playList.setName(createPlayList.getName());
        playList.setDescription(createPlayList.getDescription());
        playList.setUserId(createPlayList.getUserId());

        this.playListRepository.insert(playList);
        return playList.getId();
    }

    public List<PlayList> findAllByUserId(String userId) {
        return this.playListRepository.findByUserId(userId);
    }

    public PlayList getByPlayListId(String playListId) {
        return this.playListRepository.findById(playListId);
    }

    public void delete(String playListId) {
        PlayList playList = this.playListRepository.findById(playListId);

        this.playListRepository.remove(playList.getId());
    }

    public void addTrack(String playListId, Track track) {
        PlayList playList = this.playListRepository.findById(playListId);
        playList.addTrack(track);
        playList.increaseTrackCount();
        this.playListRepository.update(playList);
    }

    public void removeTrack(String playListId, String trackName) {
        PlayList playList = this.playListRepository.findById(playListId);
        playList.removeTrack(trackName);
        playList.decreaseTrackCount();
        this.playListRepository.update(playList);
    }

}
