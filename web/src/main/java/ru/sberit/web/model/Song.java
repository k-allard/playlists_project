package ru.sberit.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data // lombok annotation for generating getter-setters-toString-hashcode-equals methods
public class Song {
    private BigInteger id;

    @JsonProperty("playlist_id") // name of the JSON key during serialization/deserialization
    private BigInteger playlistId;

    private String name;

    @JsonProperty("created_on") // name of the JSON key during serialization/deserialization
    private Date createdOn;
}
