package com.techtalent.playlist.exception;

public class PlayListNotFoundException  extends  RuntimeException{
    public PlayListNotFoundException(String playListId) {
        super(String.format("There is no such playList with this playListId : %s ", playListId));
    }
}
