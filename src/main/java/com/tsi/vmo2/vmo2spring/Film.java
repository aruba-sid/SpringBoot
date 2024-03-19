package com.tsi.vmo2.vmo2spring;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.apache.catalina.LifecycleState;

import java.time.Year;
import java.util.List;

@Entity
@Table(name="film")
@JsonIgnoreProperties({"actors"})
public class Film {
    @Id
    @Column(name="film_id", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int filmID;

    @Column(name ="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Year releaseYear;

    @ManyToMany
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )

    private List<Actor> actors;

    public int getFilmID(){
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Year getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(Year releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
