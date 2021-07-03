package ru.sberit.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.sberit.web.model.Song;
import ru.sberit.web.service.PlaylistService;
import ru.sberit.web.service.UserService;
import ru.sberit.web.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.math.BigInteger;
import java.security.Principal;
import java.util.Optional;

@Controller
public class MusicController {
    @Autowired
    private PlaylistService playlistServiceRest;
    @Autowired
    private UserService userService;

    @GetMapping("/music")
    public ResponseEntity.BodyBuilder music(Model model, Principal principal, Authentication authentication) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("playlists", playlistServiceRest.getPlaylists(user.getId()));

        return ResponseEntity.ok();
    }

    @PostMapping("/music")
    public ResponseEntity.BodyBuilder createPlaylist(
            final @RequestParam(required = false) Optional<String> playlistName,
            final @RequestParam(required = false) Optional<String> name,
            final @RequestParam(required = false) Optional<BigInteger> playlistId,
            Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        if(playlistId.isPresent() && name.isPresent())
        {
            try {
                Optional<Song> newSong = playlistServiceRest.addSongToPlaylist(name.get(), playlistId.get());
                if (!newSong.isPresent()) {
                    model.addAttribute("songError", "A song with this name already exists");
                    return ResponseEntity.ok();
                }
            } catch (Throwable e)
            {
                model.addAttribute("songError", e.getMessage());
            }
        } else if(playlistName.isPresent())
        {
            playlistServiceRest.createPlaylist(playlistName.get(), user.getId());
        }

        model.addAttribute("playlists", playlistServiceRest.getPlaylists(user.getId()));

        return ResponseEntity.ok();
    }
}
