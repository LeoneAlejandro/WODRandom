package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;

public interface ExerciseService {
    List<Exercise> find(Long userId);

    Exercise find(Long userId, Long exerciseId);

    Exercise create(Long id, Exercise exercise);

    void delete(Long userId, Long exerciseId);

    Exercise update(Long userId, Long exerciseId, Exercise exercise);

    Exercise findByType(Long userId, Long exerciseId);
}
