package com.aleleone.WOD.Randomizer.domain.service.impl;

import static java.lang.String.format;
import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.datasource.repository.WodRepository;
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
    
    
    public WodServiceImpl(ExerciseRepository exerciseRepository, WodRepository wodRepository) {
		super();
		this.exerciseRepository = exerciseRepository;
		this.wodRepository = wodRepository;
	}



	public List<Exercise> generateWod(String username, CreationWodRequest creationWodRequest) {
        int exAmountFuerza = creationWodRequest.getExAmountFuerza();
        int exAmountCardio = creationWodRequest.getExAmountCardio();
        int exAmountOly = creationWodRequest.getExAmountOly();

        List<Exercise> allExercises = exerciseRepository.findByUserName(username);
        
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
	
	
	private List<Exercise> filterExercisesByType(List<Exercise> exercises, ExerciseType type) {
	    return exercises.stream()
	            .filter(e -> e.getExerciseType() == type)
	            .collect(Collectors.toList());
	}

	public List<Wod> find(String username) {
		return wodRepository.findByUserName(username);
	}


	public Wod find(String username, Long id) {
        List<Wod> wods = wodRepository.findByUserName(username);
        return wods.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Wod con id: %d para el usuario %s no existe", id, username))
                );
	}
	
	@Transactional
	public Wod create(String username, CreationExcerciseWodRequest requestBodyDetails) {
		String wodName = requestBodyDetails.getWodName();
		List<Long> listOfIds = requestBodyDetails.getExercisesId();
		List<Exercise> listOfExercises = new ArrayList<Exercise>();
		
		for (Long id : listOfIds) {
			Exercise exercise = exerciseRepository.findById(id)
									.orElseThrow(() -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username)));
			listOfExercises.add(exercise);
		}
		
		Wod savedWod = Wod.createWod(wodName, username, listOfExercises);
		wodRepository.save(savedWod);
		
		return savedWod;
	}
	

	public void delete(String username, Long id) {
		wodRepository.deleteById(id);
	}
}
