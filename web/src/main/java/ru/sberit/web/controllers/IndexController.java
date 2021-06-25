package ru.sberit.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import ru.sberit.web.service.PlaylistService;

@Controller
public class IndexController {

    @Autowired
    private PlaylistService playlistServiceRest;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("playlists", playlistServiceRest.getPlaylists());
        return "music";
    }
}
