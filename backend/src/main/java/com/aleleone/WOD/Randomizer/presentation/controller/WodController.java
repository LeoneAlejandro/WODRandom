package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.springframework.http.ResponseEntity.noContent;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;
import com.aleleone.WOD.Randomizer.presentation.CreationExcerciseWodRequest;
import com.aleleone.WOD.Randomizer.presentation.CreationWodRequest;

@RestController
public class WodController {

    private final WodService wodService;
    
    public WodController(WodService wodService) {
		super();
		this.wodService = wodService;
	}

	@PostMapping("/users/{userId}/generatewod")
    public List<Exercise> createWod(@PathVariable Long userId, @RequestBody CreationWodRequest wod) {
        return wodService.generateWod(userId, wod);
    }
	
	@GetMapping("/users/{userId}/wods")
    public List<Wod> getWods(@PathVariable Long userId) {
        return wodService.find(userId);
    }
	
    @GetMapping("/users/{userId}/wods/{wodId}")
    public Wod getWod(@PathVariable Long userId, @PathVariable Long wodId) {
        return wodService.find(userId, wodId);
    }
	
    @PostMapping("users/{userId}/wods")
    public Wod addWod(@PathVariable Long userId, @RequestBody CreationExcerciseWodRequest creationExcerciseWodRequest) {
    	return wodService.create(userId, creationExcerciseWodRequest);
    }
    
    @PutMapping("users/{userId}/wods/{wodId}")
    public Wod updateWod(@PathVariable Long userId,@PathVariable Long wodId, @RequestBody CreationExcerciseWodRequest creationExcerciseWodRequest) {
    	return wodService.update(userId, wodId, creationExcerciseWodRequest);
    }
    
    @DeleteMapping("users/{userId}/wods/{wodId}")
    public ResponseEntity<Void> deleteWod(@PathVariable Long userId, @PathVariable Long wodId) {
    	wodService.delete(userId, wodId);
    	return noContent().build();
    }
}
