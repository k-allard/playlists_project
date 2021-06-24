package ru.sberit.service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrackController {
    @GetMapping("/addTrack")
    public ResponseEntity<?> addTrack(String track, String artist) {
        String str =
                "Track with name \"" +
                        track +
                        "\" of artist \"" +
                        artist +
                        "\" added!";
        return ResponseEntity.ok().body(str);
    }
}
