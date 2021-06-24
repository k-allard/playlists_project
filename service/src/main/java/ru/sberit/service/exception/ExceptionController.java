package ru.sberit.service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ru.sberit.service.model.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<?> playlistNotFound(PlaylistNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.PLAYLIST_NOT_FOUND.code(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> songNotFound(SongNotFoundException ex) {
        return ResponseEntity.badRequest()
                .body(new ResponseStatusError(ErrorCodes.SONG_NOT_FOUND.code(), ex.getMessage()));
    }

}

@Data
@AllArgsConstructor
class ResponseStatusError {

    private int status;
    private String message;
}