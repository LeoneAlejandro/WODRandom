package com.aleleone.WOD.Randomizer.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>{

	List<Exercise> findByUserName(String username);
	
}
