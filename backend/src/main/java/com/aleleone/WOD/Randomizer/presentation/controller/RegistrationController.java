package com.aleleone.WOD.Randomizer.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.service.RegistrationService;
import com.aleleone.WOD.Randomizer.presentation.RegistrationRequest;

@RestController
public class RegistrationController {
	
	private final RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping("/registration")
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
}
