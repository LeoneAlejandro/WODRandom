package com.aleleone.WOD.Randomizer.presentation.controller;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @GetMapping("/users/{username}/exercises")
    public List<Exercise> getExercises(@PathVariable String username) {
        return exerciseService.find(username);
    }

    @GetMapping("/users/{username}/exercises/{id}")
    public Exercise getExercise(@PathVariable String username, @PathVariable int id) {
        return exerciseService.find(username, id);
    }


    @PostMapping("/users/{username}/exercises")
    public Exercise addExerciseById(@PathVariable String username, @RequestBody Exercise exercise) {
        return exerciseService.create(username, exercise);
    }


    @DeleteMapping("/users/{username}/exercises/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable String username, @PathVariable int id) {
        exerciseService.delete(username, id);
        return noContent().build();
    }


    @PutMapping("/users/{username}/exercises/{id}")
    public Exercise updateExercise(@PathVariable String username, @PathVariable int id, @RequestBody Exercise exercise) throws UsernameNotFoundException {
        return exerciseService.update(username, id, exercise);
    }

}
