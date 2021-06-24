package ru.sberit.service.model;

public enum ErrorCodes {

    PLAYLIST_NOT_FOUND(1001),
    SONG_NOT_FOUND(1002);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }

}