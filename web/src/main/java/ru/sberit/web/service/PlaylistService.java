package ru.sberit.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sberit.web.model.Playlist;
import ru.sberit.web.model.Song;

@Service("playlistServiceHttp")
    public class PlaylistService {
        private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
        private final WebClient localApiClient;

        @Autowired
        public PlaylistService(
                @Value("${web.playlist.Service.URL}")
                String playlistServiceURL
        ) {
            this.localApiClient = WebClient.create(playlistServiceURL);
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

        public Song[] getSongs() {
        return localApiClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/song/")
                        .build())
                .retrieve()
                .bodyToMono(Song[].class)
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
