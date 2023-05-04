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

import com.aleleone.WOD.Randomizer.domain.model.RequestBodyDetails;
import com.aleleone.WOD.Randomizer.domain.model.SavedWod;
import com.aleleone.WOD.Randomizer.domain.service.SavedWodsService;

@RestController
public class SavedWodController {

	private final SavedWodsService savedWodsService;
		
    public SavedWodController(SavedWodsService savedWodsService) {
		super();
		this.savedWodsService = savedWodsService;
	}


	@GetMapping("/users/{username}/wods")
    public List<SavedWod> getWods(@PathVariable String username) {
        return savedWodsService.find(username);
    }
	
    @GetMapping("/users/{username}/wods/{id}")
    public SavedWod getWod(@PathVariable String username, @PathVariable Long id) {
        return savedWodsService.find(username, id);
    }
	
    @PostMapping("users/{username}/wods")
    public SavedWod addWod(@PathVariable String username, @RequestBody RequestBodyDetails requestBodyDetails) {
    	return savedWodsService.create(username, requestBodyDetails);
    }
    
    @DeleteMapping("users/{username}/wods/{id}")
    public ResponseEntity<Void> deleteWod(@PathVariable String username, @PathVariable Long id) {
    	savedWodsService.delete(username, id);
    	return noContent().build();
    }
    
}
