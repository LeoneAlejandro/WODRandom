package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.presentation.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.CreationWodRequest;


public interface WodService {
    List<Exercise> generateWod(String username, CreationWodRequest creationWodRequest);
    
	List<Wod> find(String username);

	Wod find(String username, Long id);

	Wod create(String username, CreationExcerciseWodRequest requestBodyDetails);

	void delete(String username, Long id);
	
	Wod update(String username, Long id, CreationExcerciseWodRequest requestBodyDetails);
}
