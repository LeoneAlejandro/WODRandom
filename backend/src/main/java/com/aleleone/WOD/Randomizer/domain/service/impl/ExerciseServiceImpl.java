package com.aleleone.WOD.Randomizer.domain.service.impl;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Exercise.ExerciseType;
import com.aleleone.WOD.Randomizer.domain.service.ExerciseService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ExerciseServiceImpl implements ExerciseService {

	
    private final ExerciseRepository exerciseRepository;
    private final AppUserRepository appUserRepository;
//    private final ModelMapper modelMapper;
    
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, AppUserRepository appUserRepository) {
    	this.exerciseRepository = exerciseRepository;
    	this.appUserRepository = appUserRepository;
//    	this.modelMapper = modelMapper;
    }
    
    
    public AppUser returnUser(Long userId) {
        Optional<AppUser> userOptional = appUserRepository.findById(userId);
        if(!userOptional.isPresent()) {
        	throw new EntityNotFoundException(format("User with id %d doesn't exists", userId));
        }
        
        return userOptional.get();
    }
    
    @Override
    public List<Exercise> find(Long userId) {
//        AppUser user = returnUser(userId);
        return exerciseRepository.findByUserId(userId);
        
        
//		Segudno sin extraer        
//        Optional<AppUser> userOptional = appUserRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            AppUser user = userOptional.get();
//            return exerciseRepository.findByUser(user);
//        } else {
//        	throw new EntityNotFoundException(format("User with id %d doesn't exists", userId));
//        }
    	
//		original mal        
//    	AppUser user = appUserRepository.findById(userId);
//        return exerciseRepository.findByUser(user);
    }
    

    @Override
    public Exercise find(Long userId, Long exerciseId) {
    	AppUser user = returnUser(userId);
    	
        List<Exercise> exercises = exerciseRepository.findByUserId(userId);
        return exercises.stream()
                .filter(e -> e.getId().equals(exerciseId))
                .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", exerciseId, user))
                );
    }
    

    @Override
    public Exercise create(Long userId, Exercise exercise) {
    	AppUser user = returnUser(userId);
    	
        exercise.setUser(user);
        exercise.setId(null);
        exerciseRepository.save(exercise);
        return exercise;
    }
    

    @Override
    public void delete(Long userId, Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }
    

    @Override
    public Exercise update(Long userId, Long exerciseId, Exercise exercise) {
    	AppUser user = returnUser(userId);
    	
        Exercise findExercise = find(userId, exerciseId);
        if (findExercise != null) {
            exercise.setId(exerciseId);
            exercise.setUser(user);
            exerciseRepository.save(exercise);
            return exercise;
        }
        throw new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", exerciseId, user));
    }
    

	@Override
	public Exercise findByType(Long userId, Long exerciseId) {
//    	AppUser user = returnUser(userId);
    			
		List<Exercise> exercises = exerciseRepository.findByUserId(userId);
		List<Exercise> exercisesByType = new ArrayList<>();
		
		Exercise exercise = exercises.stream()
			    .filter(ex -> ex.getId().equals(exerciseId))
			    .findFirst().orElseThrow(
                        () -> new EntityNotFoundException(format("Ejercicio con id: %d para el usuario %s no existe", exerciseId, userId))
                );
		
		ExerciseType exType = exercise.getExerciseType();
		
		for (Exercise ex : exercises) {
			if(ex.getExerciseType() == exType && ex.getId() != exercise.getId()) {
				exercisesByType.add(ex);
			}
		}
		
		Random r = new Random();
		Exercise randomExerciseByType = exercisesByType.get(r.nextInt(exercisesByType.size()));
		return randomExerciseByType;
	}
}
