package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.springframework.http.ResponseEntity.noContent;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.service.ExerciseService;

@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;
    
    public ExerciseController(ExerciseService exerciseService) {
		super();
		this.exerciseService = exerciseService;
    }

	@GetMapping("/users/{userId}/exercises")
    public List<Exercise> getExercises(@PathVariable Long userId) {
        return exerciseService.find(userId);
    }

    @GetMapping("/users/{userId}/exercises/{exerciseId}")
    public Exercise getExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        return exerciseService.find(userId, exerciseId);
    }


    @PostMapping("/users/{userId}/exercises")
    public Exercise addExerciseById(@PathVariable Long userId, @RequestBody Exercise exercise) {
        return exerciseService.create(userId, exercise);
    }


    @DeleteMapping("/users/{userId}/exercises/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        exerciseService.delete(userId, exerciseId);
        return noContent().build();
    }


    @PutMapping("/users/{userId}/exercises/{exerciseId}")
    public Exercise updateExercise(@PathVariable Long userId, @PathVariable Long exerciseId, @RequestBody Exercise exercise) throws UsernameNotFoundException {
        return exerciseService.update(userId, exerciseId, exercise);
    }
    
    @PostMapping("/users/{userId}/exercises/type/{exerciseId}")
    public Exercise getExerciseByType(@PathVariable Long userId, @PathVariable Long exerciseId) throws UsernameNotFoundException {
        return exerciseService.findByType(userId, exerciseId);
    }
   
//    @PostMapping("/users/{userId}/exercises/type")
//    public Exercise getExerciseByType(@PathVariable Long userId, @RequestBody Exercise exercise) throws UsernameNotFoundException {
//        return exerciseService.findByType(userId, exercise);
//    }

}
