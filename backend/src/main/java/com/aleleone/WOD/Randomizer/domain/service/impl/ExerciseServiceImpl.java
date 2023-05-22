package com.aleleone.WOD.Randomizer.domain.service.impl;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.service.ExerciseService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	
    private final ExerciseRepository exerciseRepository;
    
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
    	this.exerciseRepository = exerciseRepository;
    }
    
    @Override
    public List<Exercise> find(String username) {
        return exerciseRepository.findByUserName(username);
    }

    @Override
    public Exercise find(String username, Long id) {
        List<Exercise> exercises = exerciseRepository.findByUserName(username);
        return exercises.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username))
                );
    }

    @Override
    public Exercise create(String username, Exercise exercise) {
        exercise.setUserName(username);
        exercise.setId(null);
        exerciseRepository.save(exercise);
        return exercise;
    }

    @Override
    public void delete(String username, Long id) {
        exerciseRepository.deleteById(id);
    }

    @Override
    public Exercise update(String username, Long id, Exercise exercise) {
        Exercise findExercise = find(username, id);
        if (findExercise != null) {
            exercise.setId(id);
            exercise.setUserName(username);
            exerciseRepository.save(exercise);
            return exercise;
        }
        throw new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username));
    }

	@Override
	public Exercise find(String username, Exercise exercise) {
		List<Exercise> exercises = exerciseRepository.findByUserName(username);
		List<Exercise> exercisesByType = new ArrayList<>();
		ExerciseType exType = exercise.getExerciseType();
		for (Exercise ex : exercises) {
			if(ex.getExerciseType() == exType && ex.getId() != exercise.getId()) {
				exercisesByType.add(ex);
			}
		}
		
		
		Random r = new Random();
		Exercise randomExerciseByType = exercisesByType.get(r.nextInt(exercisesByType.size()));
		return randomExerciseByType;
		
//		return exercise;
	}
}
