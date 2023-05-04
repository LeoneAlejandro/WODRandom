package com.aleleone.WOD.Randomizer.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.domain.model.SavedWod;

public interface SavedWodsRepository extends JpaRepository<SavedWod, Long>{

	List<SavedWod> findByUserName(String username);

	
}
