package com.aleleone.WOD.Randomizer.presentation.controller;

import com.aleleone.WOD.Randomizer.datasource.repository.ExerciseRepository;
import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;
import com.aleleone.WOD.Randomizer.domain.service.WodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WodController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WodService wodService;

    @PostMapping("/users/{username}/generatewod")
    public List<Exercise> createWod(@PathVariable String username, @RequestBody Wod wod) {
        return wodService.generateWod(username, wod);
    }
}
