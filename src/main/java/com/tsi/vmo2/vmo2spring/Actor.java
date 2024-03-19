package com.tsi.vmo2.vmo2spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="actor")
@JsonIgnoreProperties({"films"})
public class Actor {
    @Id
    @Column(name="actor_id", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int actorID;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.ALL)
    private List<Film> films;


    public int getActorID() {
        return actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Film> getFilms(){
        return  films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

}
