package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.domain.model.Exercise;
import com.aleleone.WOD.Randomizer.domain.model.Wod;

import java.util.List;


public interface WodService {
    List<Exercise> generateWod(String username, Wod wod);
}
