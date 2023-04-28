package com.aleleone.WOD.Randomizer.Exercise;

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

import com.aleleone.WOD.Randomizer.Exercise.repository.ExerciseRepository;

@RestController
public class ExerciseJpaResource {

	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@GetMapping("/users/{username}/exercises")
	public List<Exercise> getExercises(@PathVariable String username) {
		return exerciseRepository.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/exercises/{id}")
	public Exercise getExercise(@PathVariable String username, @PathVariable int id) throws UsernameNotFoundException {
		List<Exercise> exercises = exerciseRepository.findByUsername(username);
		for (Exercise exercise : exercises) {
			if(exercise.getExerciseId().equals(id)) {
				return exercise;
			}
		}
		throw new UsernameNotFoundException("Username: " + username + " or exercise with ID: " + id + " was not found");
	}
	
	@PostMapping("/users/{username}/exercises")
	public Exercise addExerciseById(@PathVariable String username, @RequestBody Exercise exercise) {
		exercise.setUsername(username);
		exercise.setExerciseId(null);
		exerciseRepository.save(exercise);
		return exercise;
	}
	
	@DeleteMapping("/users/{username}/exercises/{id}")
	public ResponseEntity<Void> deleteExercise(@PathVariable String username, @PathVariable int id) {
		exerciseRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/exercises/{id}")
	public Exercise updateExercise(@PathVariable String username, @PathVariable int id, @RequestBody Exercise exercise) throws UsernameNotFoundException {
		
		Exercise findExercise = getExercise(username, id);
		if (findExercise != null) {
			exercise.setExerciseId(id);
			exercise.setUsername(username);
			exerciseRepository.save(exercise);
			return exercise;
		} 
		throw new UsernameNotFoundException("Username: " + username + " or exercise with ID: " + id + " was not found");
	}
}
