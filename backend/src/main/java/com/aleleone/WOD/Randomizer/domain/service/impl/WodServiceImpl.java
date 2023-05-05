package com.aleleone.WOD.Randomizer.domain.service.impl;

import static com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType.CARDIO;
import static com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType.FUERZA;
import static com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType.OLY;
import static java.util.Collections.shuffle;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.datasource.repository.WodRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;
import com.aleleone.WOD.Randomizer.presentation.controller.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.controller.CreationWodRequest;

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

        List<Exercise> exercisesByUsername = exerciseRepository.findByUserName(username);
        
        shuffle(exercisesByUsername);

        List<Exercise> ejerciciosDeFuerza = exercisesByUsername.stream().filter(p -> FUERZA.equals(p.getExerciseType()))
                .collect(toList());

        List<Exercise> ejerciciosDeCardio = exercisesByUsername.stream().filter(p -> CARDIO.equals(p.getExerciseType()))
                .collect(toList());

        List<Exercise> ejerciciosDeOly = exercisesByUsername.stream().filter(p -> OLY.equals(p.getExerciseType()))
                .collect(toList());


        int lengthFuerza = ejerciciosDeFuerza.size();
        int lengthCardio = ejerciciosDeCardio.size();
        int lengthOly = ejerciciosDeOly.size();

        if (lengthFuerza < exAmountFuerza || lengthCardio < exAmountCardio || lengthOly < exAmountOly) {
//			throw new IndexOutOfBoundsException(); "Elegiste muchos ejercicios para tu lista"
            return null;
        }


        List<Exercise> exFuerzas = ejerciciosDeFuerza.subList(0, exAmountFuerza);
        List<Exercise> exCardios = ejerciciosDeCardio.subList(0, exAmountCardio);
        List<Exercise> exOlys = ejerciciosDeOly.subList(0, exAmountOly);

        List<Exercise> wodGenerado = of(exFuerzas, exCardios, exOlys)
                .flatMap(Collection::stream)
                .collect(toList());

        shuffle(wodGenerado);

        return wodGenerado;
    }
	

	public List<Wod> find(String username) {
		return wodRepository.findByUserName(username);
	}



	public Optional<Wod> find(Long id) {
        Optional<Wod> exercises = wodRepository.findById(id);
//        return exercises.stream()
//                .filter(e -> e.getId().equals(id))
//                .findFirst().orElseThrow(
//                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", id, username))
//                );
        return exercises;
	}

	
	@Transactional
	public Wod create(String username, CreationExcerciseWodRequest requestBodyDetails) {
		String wodName = requestBodyDetails.getWodName();
		List<Long> listOfIds = requestBodyDetails.getExercisesId();
		List<Exercise> listOfExercises = new ArrayList<Exercise>();
				
		for (Long id : listOfIds) {
			Exercise exercise = exerciseRepository.getById(id);
			listOfExercises.add(exercise);
		}
		
		Wod savedWod = Wod.createWod(wodName, username, listOfExercises);
		wodRepository.save(savedWod);
		
	
		return null;
	}
	

	public void delete(String username, Long id) {
		wodRepository.deleteById(id);
	}
}
