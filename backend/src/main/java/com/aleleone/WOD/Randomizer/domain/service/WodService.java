package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.presentation.controller.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.controller.CreationWodRequest;

import java.util.List;


public interface WodService {
    List<Exercise> generateWod(String username, CreationWodRequest creationWodRequest);
    
	List<Wod> find(String username);

	Wod find(String username, Long id);

	Wod create(String username, CreationExcerciseWodRequest requestBodyDetails);

	void delete(String username, Long id);
}
