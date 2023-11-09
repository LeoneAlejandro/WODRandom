package com.aleleone.WOD.Randomizer.datasource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aleleone.WOD.Randomizer.domain.model.Wod;

public interface WodRepository extends JpaRepository<Wod, Long>{

	List<Wod> findByUserId(Long userId);
	
}
