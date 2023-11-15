package com.aleleone.WOD.Randomizer.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleleone.WOD.Randomizer.domain.model.AppUserDTO;
import com.aleleone.WOD.Randomizer.domain.service.AuthenticationService;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;
import com.aleleone.WOD.Randomizer.presentation.ChangePasswordRequest;

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

	@PostMapping("/changepassword/{userId}")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, @PathVariable Long userId) {
		authenticationService.changePassword(request, userId);
		return ResponseEntity.ok("Password changed succesfully");
	}
	
//	@GetMapping("/getuser/{id}")
//	public ResponseEntity<AppUserDTO> findUserDTO(@PathVariable Long id) {
//		return ResponseEntity.ok(authenticationService.findUserDTOById(id));
//	}
}
