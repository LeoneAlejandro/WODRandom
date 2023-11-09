package com.aleleone.WOD.Randomizer.domain.service.impl;

import static java.lang.String.format;
import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.datasource.repository.WodRepository;
import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;
import com.aleleone.WOD.Randomizer.presentation.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.CreationWodRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class WodServiceImpl implements WodService {

    private final ExerciseRepository exerciseRepository;
    private final WodRepository wodRepository;
    private final AppUserRepository appUserRepository;
    
    public WodServiceImpl(ExerciseRepository exerciseRepository, WodRepository wodRepository, AppUserRepository appUserRepository) {
    	super();
    	this.exerciseRepository = exerciseRepository;
    	this.wodRepository = wodRepository;
    	this.appUserRepository = appUserRepository;
    }
    
    public AppUser returnUser(Long userId) {
        Optional<AppUser> userOptional = appUserRepository.findById(userId);
        if(!userOptional.isPresent()) {
        	throw new EntityNotFoundException(format("User with id %d doesn't exists", userId));
        }
        
        return userOptional.get();
    }
    
    
	private Wod createWodFromRequest(Long userId, CreationExcerciseWodRequest requestBodyDetails) {
		AppUser user = returnUser(userId);
		String wodName = requestBodyDetails.getWodName();
		List<Long> listOfIds = requestBodyDetails.getExercisesId();
		List<Exercise> listOfExercises = new ArrayList<Exercise>();

		for (Long exerciseId : listOfIds) {
			Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new EntityNotFoundException(
					format("Ejercicio con id: %d para el usuario %s no existe", exerciseId, user)));
			listOfExercises.add(exercise);
		}
		return Wod.createWod(wodName, user, listOfExercises);
	}


	private List<Exercise> filterExercisesByType(List<Exercise> exercises, ExerciseType type) {
		return exercises.stream()
				.filter(e -> e.getExerciseType() == type)
				.collect(Collectors.toList());
	}

	
//	API	METHODS
	
	@Override
	public List<Exercise> generateWod(Long userId, CreationWodRequest creationWodRequest) {
		int exAmountFuerza = creationWodRequest.getExAmountFuerza();
		int exAmountCardio = creationWodRequest.getExAmountCardio();
		int exAmountOly = creationWodRequest.getExAmountOly();
		
		AppUser user = returnUser(userId);
		List<Exercise> allExercises = exerciseRepository.findByUser(user);

		shuffle(allExercises);

		List<Exercise> ejerciciosDeFuerza = filterExercisesByType(allExercises, ExerciseType.FUERZA);
		List<Exercise> ejerciciosDeCardio = filterExercisesByType(allExercises, ExerciseType.CARDIO);
		List<Exercise> ejerciciosDeOly = filterExercisesByType(allExercises, ExerciseType.OLY);

		int lengthFuerza = ejerciciosDeFuerza.size();
		int lengthCardio = ejerciciosDeCardio.size();
		int lengthOly = ejerciciosDeOly.size();

		if (lengthFuerza < exAmountFuerza || lengthCardio < exAmountCardio || lengthOly < exAmountOly) {
			throw new IndexOutOfBoundsException("Elegiste muchos ejercicios para tu lista");
		}

		List<Exercise> wodGenerado = new ArrayList<>();
		
		for (int i = 0; i < exAmountFuerza; i++) {
			wodGenerado.add(ejerciciosDeFuerza.get(i));
		}
		for (int i = 0; i < exAmountCardio; i++) {
			wodGenerado.add(ejerciciosDeCardio.get(i));
		}
		for (int i = 0; i < exAmountOly; i++) {
			wodGenerado.add(ejerciciosDeOly.get(i));
		}

		shuffle(wodGenerado);

		return wodGenerado;
	}
			
	@Override
	public List<Wod> find(Long userId) {
//		AppUser user = returnUser(userId);
		return wodRepository.findByUserId(userId);
	}

	
	@Override
	public Wod find(Long userId, Long wodId) {
        List<Wod> wods = wodRepository.findByUserId(userId);
        return wods.stream()
                .filter(e -> e.getId().equals(wodId))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Wod con id: %d para el usuario %s no existe", wodId, userId))
                );
	}
	
	
	@Override
	@Transactional
	public Wod create(Long userId, CreationExcerciseWodRequest requestBodyDetails) {
//		AppUser user = returnUser(userId);
		Wod savedWod = createWodFromRequest(userId, requestBodyDetails);
		wodRepository.save(savedWod);
		
		return savedWod;
	}
	
	
	@Override
	public Wod update(Long userId, Long wodId, CreationExcerciseWodRequest requestBodyDetails) {
		Wod findWod = find(userId, wodId);
		if (findWod != null) {
			Wod updatedWod = createWodFromRequest(userId, requestBodyDetails);
			updatedWod.setId(wodId);
			wodRepository.save(updatedWod);

			return updatedWod;
		}
		throw new EntityNotFoundException(format("Wod con id: %d para el usuario %s no existe", wodId, userId));
	}
	
	
	@Override
	public void delete(Long userId, Long wodId) {
		wodRepository.deleteById(wodId);
	}
}
