package com.aleleone.WOD.Randomizer.domain.service.impl;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.domain.service.RegistrationService;
import com.aleleone.WOD.Randomizer.presentation.RegistrationRequest;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public String register(RegistrationRequest request) {
		return "works";
	}

}
