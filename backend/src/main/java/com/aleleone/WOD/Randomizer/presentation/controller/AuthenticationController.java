package com.aleleone.WOD.Randomizer.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.service.AuthenticationService;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;

@RestController
public class AuthenticationController {
	
	private AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
	  return ResponseEntity.ok(authenticationService.authenticate(request));
	}

}
