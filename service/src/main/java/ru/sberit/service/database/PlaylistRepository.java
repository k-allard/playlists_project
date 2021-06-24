package ru.sberit.service.database;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.sberit.service.model.Playlist;
import ru.sberit.service.model.Song;

public interface PlaylistRepository extends JpaRepository<Playlist, BigInteger> {
    public Optional<Playlist> findByName(String name);

    @Query("select s from Song s where s.playlistId = ?1")
    public Collection<Song> getSongs(BigInteger playlistId);
}
