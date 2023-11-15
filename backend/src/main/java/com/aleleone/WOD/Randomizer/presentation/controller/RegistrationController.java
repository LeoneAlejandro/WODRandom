package com.aleleone.WOD.Randomizer.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.service.AuthenticationService;
import com.aleleone.WOD.Randomizer.domain.service.RegistrationService;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;
import com.aleleone.WOD.Randomizer.presentation.RegistrationRequest;

@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {
	
	private final RegistrationService registrationService;
	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping
	public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
		try {
			String token = registrationService.register(request);
			return ResponseEntity.ok(token);
		} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "confirm")
	public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        try {
            registrationService.confirmToken(token);
            return ResponseEntity.ok("Confirmed");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
	}
	
//	@PostMapping
//	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//		return ResponseEntity.ok(authenticationService.authenticate(request));
//	}
	
}
