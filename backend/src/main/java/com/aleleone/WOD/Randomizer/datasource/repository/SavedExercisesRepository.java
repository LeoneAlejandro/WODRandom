package com.aleleone.WOD.Randomizer.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.domain.model.SavedExercise;

public interface SavedExercisesRepository extends JpaRepository<SavedExercise, Long> {

}
