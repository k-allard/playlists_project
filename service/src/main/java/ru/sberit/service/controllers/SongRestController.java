package ru.sberit.service.controllers;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sberit.service.model.Playlist;
import ru.sberit.service.model.Song;
import ru.sberit.service.service.PlaylistService;

@RestController
@RequestMapping("/song")
public class SongRestController {
    public PlaylistService service;

    @Qualifier("playlistService")
    @Autowired
    public void setService(PlaylistService service) {
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Song> getAllSongs() {
        return service.getSongs(null);
    }

}
