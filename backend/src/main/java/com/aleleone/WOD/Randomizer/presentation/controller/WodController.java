package com.aleleone.WOD.Randomizer.presentation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;

@RestController
public class WodController {

    private final WodService wodService;
    
    public WodController(WodService wodService) {
		super();
		this.wodService = wodService;
	}



	@PostMapping("/users/{username}/generatewod")
    public List<Exercise> createWod(@PathVariable String username, @RequestBody Wod wod) {
        return wodService.generateWod(username, wod);
    }
}
