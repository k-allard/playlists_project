package ru.sberit.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import ru.sberit.web.service.PlaylistService;
import ru.sberit.web.service.UserService;
import ru.sberit.web.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.math.BigInteger;
import java.security.Principal;

@Controller
public class MusicController {
    @Autowired
    private PlaylistService playlistServiceRest;
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(UserService principal) {
//        return principal.getName();
//    }

    @GetMapping("/music")
    public ResponseEntity.BodyBuilder music(Model model, Principal principal, Authentication authentication) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("username", user.getUsername());

        model.addAttribute("playlists", playlistServiceRest.getPlaylists(user.getId()));

        return ResponseEntity.ok();
    }

    @PostMapping("/music")
    public ResponseEntity.BodyBuilder createPlaylist(
            final @RequestParam String playlistName,
            Model model, Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("userId", user.getId());
        model.addAttribute("username", user.getUsername());

        playlistServiceRest.createPlaylist(playlistName, user.getId());
        model.addAttribute("playlists", playlistServiceRest.getPlaylists(user.getId()));

        return ResponseEntity.ok();
    }

}
