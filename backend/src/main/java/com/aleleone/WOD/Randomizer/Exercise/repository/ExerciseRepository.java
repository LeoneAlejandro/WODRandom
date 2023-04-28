package com.aleleone.WOD.Randomizer.Exercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.Exercise.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{

	List<Exercise> findByUsername(String username);
	
}
