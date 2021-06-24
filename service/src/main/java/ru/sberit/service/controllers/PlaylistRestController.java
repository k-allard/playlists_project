package ru.sberit.service.controllers;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.sberit.service.model.Playlist;
import ru.sberit.service.model.Song;
import ru.sberit.service.service.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistRestController {

	public PlaylistService service;

	@Qualifier("playlistService")
	@Autowired
	public void setService(PlaylistService service) {
		this.service = service;
	}

	@GetMapping("/about")
	public String root() {
		return "application is runnning!";
	}

	@GetMapping("/all")
	public Iterable<Playlist> getAllPlaylists() {
		return service.getAllPlaylists();
	}

	@GetMapping("/")
	public Playlist getPlaylistById(final @RequestParam("id") BigInteger playlistId) {
		return service.getPlaylistById(playlistId);
	}

	@PostMapping("/")
	public Optional<Playlist> createPlaylist(final @RequestParam String name) {
		return service.createPlaylist(name);
	}

	@DeleteMapping("/")
	public void deletePlaylist(final @RequestParam("id") BigInteger playlistId) {
		service.deletePlaylist(playlistId);
	}

	@GetMapping("/songs")
	public Iterable<Song> getSongsInPlaylist(@RequestParam("from_playlist") BigInteger playlistId) {
		return service.getSongs(playlistId);
	}

	@DeleteMapping("/songs")
	public void deleteAllSongsInPlaylist(final @RequestParam("from_playlist") BigInteger playlistId) {
		service.deleteSongs(playlistId);
	}

	@PostMapping("/song")
	public Song addSongToPlaylist(final @RequestParam("to_playlist") BigInteger playlistId,
			final @RequestBody Song song) {
		return service.addSong(playlistId, song);
	}


	@PutMapping("/song/move")
	public boolean moveSongToDifferentPlaylist(@PathVariable("song_id") BigInteger songId,
			@RequestParam("to_playlist") BigInteger toPlaylistId) {
		return service.moveSong(songId, toPlaylistId);
	}

	@DeleteMapping("/song")
	public void deleteFromPlaylist(final @PathVariable("from_playlist") BigInteger playlistId,
			final @PathVariable("song_id") BigInteger songId) {
		service.deleteSong(playlistId, songId);
	}

}
