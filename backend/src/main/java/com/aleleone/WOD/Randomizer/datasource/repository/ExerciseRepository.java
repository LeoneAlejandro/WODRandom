package com.aleleone.WOD.Randomizer.datasource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

	List<Exercise> findByUser(AppUser user);
	
	Optional<Exercise> findById(Long id);
}
