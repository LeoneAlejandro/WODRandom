package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseService {

	@Autowired
	ExerciseRepository exerciseRepository;
	
	public List<Exercise> getExercisesForUsername(String username) {
		return exerciseRepository.findByUsername(username);
	}
	
	
	public Exercise getExerciseForUsername(String username, int id) {
		List<Exercise> exercises = exerciseRepository.findByUsername(username);
		
		for (Exercise exercise : exercises) {
			if(exercise.getExerciseId().equals(id)) {
				return exercise;
			}
		}
		throw new EntityNotFoundException("Ejercicio con id: " + id + " para el usuario " + username + " no existe");
	}
	
	
	public Exercise addExercise(String username, Exercise exercise) {
		exercise.setUsername(username);
		exercise.setExerciseId(null);
		exerciseRepository.save(exercise);
		return exercise;
	}
	
	
	public ResponseEntity<Void> deleteExercise(@PathVariable String username, @PathVariable int id) {
		exerciseRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	public Exercise updateExercise(String username, int id, Exercise exercise) {
		Exercise findExercise = getExerciseForUsername(username, id);
		if (findExercise != null) {
			exercise.setExerciseId(id);
			exercise.setUsername(username);
			exerciseRepository.save(exercise);
			return exercise;
		} 
		throw new EntityNotFoundException("Ejercicio con id: " + id + " para el usuario " + username + " no existe");
	}
}
