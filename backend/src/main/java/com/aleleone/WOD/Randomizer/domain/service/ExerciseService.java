package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;

public interface ExerciseService {
    List<Exercise> find(String username);

    Exercise find(String username, int id);

    Exercise create(String username, Exercise exercise);

    void delete(String username, int id);

    Exercise update(String username, int id, Exercise exercise);
}
