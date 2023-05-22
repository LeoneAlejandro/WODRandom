package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;

public interface ExerciseService {
    List<Exercise> find(String username);

    Exercise find(String username, Long id);

    Exercise create(String username, Exercise exercise);

    void delete(String username, Long id);

    Exercise update(String username, Long id, Exercise exercise);

    Exercise find(String username, Exercise exercise);
}
