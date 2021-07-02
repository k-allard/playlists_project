package ru.sberit.service.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data       // lombok annotation for generating getter-setters-toString-hashcode-equals methods
@Entity     // denoting this is a JPA entity object
@Table(name = "playlist")   // denoting which table this class should be mapped to
public class Playlist {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id // primary key of the table
    private BigInteger id;

    private String name;
    private BigInteger userId;

    @Column(name = "created_at") // name of the database column this field should be mapped to
    @JsonProperty("created_on") // name of the database column this field should be mapped to
    private Date createdOn;

    @ElementCollection(targetClass = java.util.HashSet.class) // specifies a collection of instances of a basic type or embeddable/nested class
    @OneToMany(cascade = CascadeType.ALL) // specifies a many-valued association with one-to-many multiplicity
    @JoinColumn // specifies a column for joining an entity association or element collection
    private Collection<Song> songs;

}