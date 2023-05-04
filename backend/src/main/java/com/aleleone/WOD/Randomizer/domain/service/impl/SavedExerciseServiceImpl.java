package com.aleleone.WOD.Randomizer.domain.service.impl;

import com.aleleone.WOD.Randomizer.datasource.repository.SavedExercisesRepository;
import com.aleleone.WOD.Randomizer.domain.model.SavedExercise;
import com.aleleone.WOD.Randomizer.domain.service.SavedExerciseService;

public class SavedExerciseServiceImpl implements SavedExerciseService {

	private final SavedExercisesRepository savedExercisesRepository;
	
	
	
	public SavedExerciseServiceImpl(SavedExercisesRepository savedExercisesRepository) {
		super();
		this.savedExercisesRepository = savedExercisesRepository;
	
	}

	@Override
	public SavedExercise create(SavedExercise savedExercise) {
		savedExercisesRepository.save(savedExercise);
		return null;
	}

}
