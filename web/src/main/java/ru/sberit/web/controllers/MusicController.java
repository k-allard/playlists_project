package ru.sberit.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import ru.sberit.web.service.PlaylistService;

@Controller
public class MusicController {
    @Autowired
    private PlaylistService playlistServiceRest;

    @GetMapping("/music")
    public ResponseEntity.BodyBuilder music(Model model) {
        model.addAttribute("playlists", playlistServiceRest.getPlaylists());
        return ResponseEntity.ok();
    }
}
