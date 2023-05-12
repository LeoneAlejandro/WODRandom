package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.springframework.http.ResponseEntity.noContent;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/users/{username}/generatewod")
    public List<Exercise> createWod(@PathVariable String username, @RequestBody CreationWodRequest wod) {
        return wodService.generateWod(username, wod);
    }
	
	@GetMapping("/users/{username}/wods")
    public List<Wod> getWods(@PathVariable String username) {
        return wodService.find(username);
    }
	
    @GetMapping("/users/{username}/wods/{id}")
    public Wod getWod(@PathVariable String username, @PathVariable Long id) {
        return wodService.find(username, id);
    }
	
    @PostMapping("users/{username}/wods")
    public Wod addWod(@PathVariable String username, @RequestBody CreationExcerciseWodRequest creationExcerciseWodRequest) {
    	return wodService.create(username, creationExcerciseWodRequest);
    }
    
    @DeleteMapping("users/{username}/wods/{id}")
    public ResponseEntity<Void> deleteWod(@PathVariable String username, @PathVariable Long id) {
    	wodService.delete(username, id);
    	return noContent().build();
    }
}
