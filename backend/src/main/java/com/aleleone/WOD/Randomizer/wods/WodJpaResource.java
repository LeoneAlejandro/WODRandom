package com.aleleone.WOD.Randomizer.wods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.Exercise.Exercise;
import com.aleleone.WOD.Randomizer.Exercise.repository.ExerciseRepository;

@RestController
public class WodJpaResource {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	WodGenerator wodGenerator;
	
	@PostMapping("/users/{username}/generatewod")
	public List<Exercise> createWod(@PathVariable String username, @RequestBody WodRequestDetails wod) {		
		int exAmountFuerza = wod.getExAmountFuerza();
		int exAmountCardio = wod.getExAmountCardio();
		int exAmountOly = wod.getExAmountOly();
		
		List<Exercise> exercisesByUsername = exerciseRepository.findByUsername(username);
		
		List<Exercise> wodGenerated = wodGenerator.generateWod(exercisesByUsername, exAmountFuerza, exAmountCardio, exAmountOly );
		
		return wodGenerated;
	}
}
