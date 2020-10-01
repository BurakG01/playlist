package com.techtalent.playlist.controller;

import com.techtalent.playlist.contract.request.CreatePlayList;
import com.techtalent.playlist.domain.PlayList;
import com.techtalent.playlist.domain.Track;
import com.techtalent.playlist.service.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlayListController {
    private final PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreatePlayList createPlayList) {
        String playListId = playListService.create(createPlayList);
        URI location = URI.create(String.format("/playlist/%s", playListId));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{playListId}")
    public ResponseEntity findById(@PathVariable String playListId) {
        PlayList playList = playListService.getByPlayListId(playListId);
        return ResponseEntity.ok(playList);
    }

    @GetMapping("/{userId}/user-playlist")
    public ResponseEntity findAllByUserId(@PathVariable String userId) {
        List<PlayList> playLists = playListService.findAllByUserId(userId);
        return ResponseEntity.ok(playLists);
    }

    @DeleteMapping("/{playListId}")
    public ResponseEntity deletePlaylist(@PathVariable String playListId) {
        playListService.delete(playListId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{playListId}/track")
    public ResponseEntity<Void> addTrack(@PathVariable String playListId, @RequestBody Track track) {
        playListService.addTrack(playListId, track);
        URI location = URI.create(String.format("/playlist/%s", playListId));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{playListId}/track/{trackName}")
    public ResponseEntity<Void> deleteTrack(@PathVariable String playListId, @PathVariable String trackName) {
        playListService.removeTrack(playListId, trackName);
        URI location = URI.create(String.format("/playlist/%s", playListId));
        return ResponseEntity.created(location).build();
    }


}
