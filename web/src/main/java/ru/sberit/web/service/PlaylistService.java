package ru.sberit.web.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sberit.web.model.Playlist;

@Service("playlistServiceHttp")
    public class PlaylistService {
        private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
        private final WebClient localApiClient;

        @Autowired
        public PlaylistService() {
            this.localApiClient = WebClient.create("http://localhost:8081");
        }

        public Playlist[] getPlaylists() {
            return localApiClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/playlist/all")
                            .build())
                    .retrieve()
                    .bodyToMono(Playlist[].class)
                    .block(REQUEST_TIMEOUT);
        }

    //        public Playlist getPlaylist(long id) {
//            return localApiClient
//                    .get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/playlist/")
//                            .queryParam("id", id)
//                            .build())
//                    .retrieve()
//                    .bodyToMono(Playlist.class)
//                    .block(REQUEST_TIMEOUT);
//        }

    }
