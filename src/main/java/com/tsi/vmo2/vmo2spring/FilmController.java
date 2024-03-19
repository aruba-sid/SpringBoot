package com.tsi.vmo2.vmo2spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class FilmController {
    @Autowired
    private FilmRepository filmRep;

    public FilmController(FilmRepository filmRep){
        this.filmRep = filmRep;
    }

//  CREATE
    @PostMapping("film")
    public ResponseEntity<Film> create(@RequestBody Film newFilm){
     Film film = filmRep.save(newFilm);
     return new ResponseEntity<>(film, HttpStatus.CREATED);
    }

//  READ
    @GetMapping("/allFilms")
    public Iterable<Film> getAllFilms() {
        return filmRep.findAll();
    }


//  UPDATE
    @PutMapping("/updateFilm/{id}")
    public Film updateFilm(@RequestBody Film newFilm, @PathVariable("id") int filmID){
        return filmRep.findById(filmID)
                .map(film ->{
                    film.setTitle(newFilm.getTitle());
                    film.setDescription(newFilm.getDescription());
                    film.setReleaseYear(newFilm.getReleaseYear());
                    return filmRep.save(film);
                })
                .orElseGet(() -> {
                    newFilm.setFilmID(filmID);
                    return filmRep.save(newFilm);
                });
    }

//  DELETE
    @DeleteMapping("/deleteFilm/{id}")
    public void deleteFilmByID(@PathVariable("id") int filmID){
        filmRep.deleteById(filmID);
    }

//	Mapping films with their cast
    @GetMapping("/{filmId}/cast")
    public ResponseEntity<List<String>> getActorsByFilm(@PathVariable int filmId){
        Film film = filmRep.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        List<String> filmCast = film.getActors().stream()
                .map(actor -> actor.getFirstName() + " " + actor.getLastName())
                .collect(Collectors.toList());


        return ResponseEntity.ok(filmCast);
    }
}
