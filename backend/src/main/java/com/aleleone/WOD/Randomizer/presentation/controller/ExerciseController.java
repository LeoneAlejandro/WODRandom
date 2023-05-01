package com.aleleone.WOD.Randomizer.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ExerciseService exerciseService;
	
	
	@GetMapping("/users/{username}/exercises")
	public List<Exercise> getExercises(@PathVariable String username) {
		return exerciseService.getExercisesForUsername(username);
	}
	
	@GetMapping("/users/{username}/exercises/{id}")
	public Exercise getExercise(@PathVariable String username, @PathVariable Long id) {
		return exerciseService.getExerciseForUsername(username, id);
	}
	
	
	@PostMapping("/users/{username}/exercises")
	public Exercise addExerciseById(@PathVariable String username, @RequestBody Exercise exercise) {
		return exerciseService.addExercise(username, exercise);
	}
	
	
	@DeleteMapping("/users/{username}/exercises/{id}")
	public ResponseEntity<Void> deleteExercise(@PathVariable String username, @PathVariable int id) {
		return exerciseService.deleteExercise(username, id);
	}
	
	
	@PutMapping("/users/{username}/exercises/{id}")
	public Exercise updateExercise(@PathVariable String username, @PathVariable Long id, @RequestBody Exercise exercise) throws UsernameNotFoundException {
		return exerciseService.updateExercise(username, id, exercise);
	}
}
