package com.aleleone.WOD.Randomizer.domain.service.impl;

import static java.lang.String.format;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.SavedWodsRepository;
import com.aleleone.WOD.Randomizer.domain.model.RequestBodyDetails;
import com.aleleone.WOD.Randomizer.domain.model.SavedExercise;
import com.aleleone.WOD.Randomizer.domain.model.SavedWod;
import com.aleleone.WOD.Randomizer.domain.service.SavedWodsService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SavedWodServiceImpl implements SavedWodsService {

	private final SavedWodsRepository savedWodsRepository;
		
	public SavedWodServiceImpl(SavedWodsRepository savedWodsRepository) {
		super();
		this.savedWodsRepository = savedWodsRepository;
	}


	@Override
	public List<SavedWod> find(String username) {
		return savedWodsRepository.findByUserName(username);
	}


	@Override
	public SavedWod find(String username, Long id) {
        List<SavedWod> exercises = savedWodsRepository.findByUserName(username);
        return exercises.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username))
                );
	}

	@Override
	public SavedWod create(String username, RequestBodyDetails requestBodyDetails) {
//		SavedWod savedWod = savedWodsRepository.saveAndFlush(saveWod);
		
		System.out.println(requestBodyDetails);
//		SavedWod savedWod = requestBodyDetails.getSavedWod();
//		System.out.println(savedWod);
//		
//		List<SavedExercise> listOfExercises = requestBodyDetails.getListOfExercisesToSave();
//		
//		for (SavedExercise savedExercise : listOfExercises) {
//			savedExercise.setSavedWodId(savedWod);
//			System.out.println(savedExercise);
//		}
		
		return null;
	}
	
	@Override
	public void delete(String username, Long id) {
		savedWodsRepository.deleteById(id);
		
	}

	
}
