package ru.sberit.web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.Duration;
import java.util.Optional;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.sberit.web.model.Playlist;
import ru.sberit.web.model.Song;

@Service("playlistServiceHttp")
    public class PlaylistService {
        private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);
        private final WebClient localApiClient;

        @Autowired
        public PlaylistService(
                @Value("${web.playlist.Service.URL}")
                String playlistServiceURL
        ) {
            this.localApiClient = WebClient.create(playlistServiceURL);
        }

        public Optional<Playlist> createPlaylist(String name, BigInteger userId)
        {

            return localApiClient
                    .post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/playlist/")
                            .build())
                    .body(BodyInserters
                            .fromFormData("name", name)
                            .with("userId", userId.toString()))
                    .retrieve()
                    .bodyToMono(Playlist.class)
                    .blockOptional()
                    ;
        }

    public Optional<Song> addSongToPlaylist(String name, BigInteger playlistId)
    {
        Song song = new Song();
        song.setName(name);

        return localApiClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/playlist/song")
                        .queryParam("to_playlist", playlistId.toString())
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromValue(song)
                        )
                .retrieve()
                .bodyToMono(Song.class)
                .blockOptional()
                ;
    }

        public Playlist[] getPlaylists(BigInteger userId) {

            return localApiClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/playlist/all")
                            .queryParam("userId", userId)
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
