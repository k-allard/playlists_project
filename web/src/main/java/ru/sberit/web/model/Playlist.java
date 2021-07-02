package ru.sberit.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

@Data       // lombok annotation for generating getter-setters-toString-hashcode-equals methods
public class Playlist {

    private BigInteger id;

    private String name;

    private BigInteger userId;

    @JsonProperty("created_on") // name of the database column this field should be mapped to
    private Date createdOn;

    private Collection<Song> songs;

}