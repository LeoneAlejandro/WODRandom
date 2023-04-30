package com.aleleone.WOD.Randomizer.domain.service.impl;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.service.ExerciseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public List<Exercise> find(String username) {
        return exerciseRepository.findByUsername(username);
    }


    public Exercise find(String username, int id) {
        List<Exercise> exercises = exerciseRepository.findByUsername(username);
        return exercises.stream()
                .filter(e -> e.getExerciseId().equals(id))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username))
                );
    }


    public Exercise create(String username, Exercise exercise) {
        exercise.setUsername(username);
        exercise.setExerciseId(null);
        exerciseRepository.save(exercise);
        return exercise;
    }


    public void delete(String username, int id) {
        exerciseRepository.deleteById(id);
    }


    public Exercise update(String username, int id, Exercise exercise) {
        Exercise findExercise = find(username, id);
        if (findExercise != null) {
            exercise.setExerciseId(id);
            exercise.setUsername(username);
            exerciseRepository.save(exercise);
            return exercise;
        }
        throw new EntityNotFoundException("Ejercicio con id: " + id + " para el usuario " + username + " no existe");
    }
}
