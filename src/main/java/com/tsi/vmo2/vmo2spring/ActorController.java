package com.tsi.vmo2.vmo2spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class ActorController {
    @Autowired
    private ActorRepository actorRep;

    public ActorController(ActorRepository actorRep){
        this.actorRep = actorRep;
    }

    //	CREATE
    @PostMapping("actor")
    public ResponseEntity<Actor> create(@RequestBody Actor newActor){
        Actor actor = actorRep.save(newActor);
        return new ResponseEntity<>(actor, HttpStatus.CREATED);
    }

    //	READ
    @GetMapping("/allActors")
    public Iterable<Actor> getAllActors() {
        return actorRep.findAll();
    }

    @GetMapping("actor/{id}")
    public Actor getActorByID(@PathVariable("id") int actorID) {
        return actorRep.findById(actorID).
                orElseThrow(() -> new ResourceAccessException("Actor not found"));
    }

    //	UPDATE
    @PutMapping("/updateActor/{id}")
    public Actor updateActor(@RequestBody Actor newActor, @PathVariable("id") int actorID){
        return actorRep.findById(actorID)
                .map(actor -> {
                    actor.setFirstName(newActor.getFirstName());
                    actor.setLastName(newActor.getLastName());
                    return actorRep.save(actor);
                })
                .orElseGet(() ->{
                    newActor.setActorID(actorID);
                    return actorRep.save(newActor);
                });
    }

    //	DELETE
    @DeleteMapping("/deleteActor/{id}")
    public void deleteActorByID(@PathVariable("id") int actorID) {
        actorRep.deleteById(actorID);
    }

    //	Mapping actors with their films
    @GetMapping("/films/{actorId}")
    public ResponseEntity<List<String>> getFilmsByActor(@PathVariable int actorId) {
        Actor actor = actorRep.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor not found"));

        List<String> filmTitles = actor.getFilms().stream()
                .map(Film::getTitle)
                .collect(Collectors.toList());

        return ResponseEntity.ok(filmTitles);
    }
}
