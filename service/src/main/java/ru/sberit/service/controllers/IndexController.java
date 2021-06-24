package ru.sberit.service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public ResponseEntity<?> index() {
        return ResponseEntity.ok().build();
    }
}
