package com.aleleone.WOD.Randomizer.domain.service;

import java.util.List;

import com.aleleone.WOD.Randomizer.domain.model.RequestBodyDetails;
import com.aleleone.WOD.Randomizer.domain.model.SavedWod;

public interface SavedWodsService {

	List<SavedWod> find(String username);

	SavedWod find(String username, Long id);

	SavedWod create(String username, RequestBodyDetails requestBodyDetails);

	void delete(String username, Long id);
}
