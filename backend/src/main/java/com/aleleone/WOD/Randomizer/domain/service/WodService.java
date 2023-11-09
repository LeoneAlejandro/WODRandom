package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.presentation.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.CreationWodRequest;


public interface WodService {
    List<Exercise> generateWod(Long userId, CreationWodRequest creationWodRequest);
    
	List<Wod> find(Long userId);

	Wod find(Long userId, Long wodId);

	Wod create(Long userId, CreationExcerciseWodRequest requestBodyDetails);

	void delete(Long userId, Long wodId);
	
	Wod update(Long userId, Long wodId, CreationExcerciseWodRequest requestBodyDetails);
}
