package ru.sberit.service.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data // lombok annotation for generating getter-setters-toString-hashcode-equals methods
@Entity // denoting this is a JPA entity object
@Table(name = "song") // denoting which table this class should be mapped to
@javax.persistence.NamedNativeQuery(name = "songsByPlaylistId",
        query = "select id, name, playlist_id, cover_url, created_on from song s where s.playlist_id = ?",
        resultClass = Song.class)
public class Song {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // primary key of the table
    private BigInteger id;

    @Column(name = "playlist_id") // name of the database column this field should be mapped to
    @JsonProperty("playlist_id") // name of the JSON key during serialization/deserialization
    private BigInteger playlistId;

    private String name;

    @Column(name = "cover_url") // name of the database column this field should be mapped to
    @JsonProperty("cover_url") // name of the JSON key during serialization/deserialization
    private String coverUrl;

    @Column(name = "created_at") // name of the database column this field should be mapped to
    @JsonProperty("created_on") // name of the JSON key during serialization/deserialization
    private Date createdOn;

}
