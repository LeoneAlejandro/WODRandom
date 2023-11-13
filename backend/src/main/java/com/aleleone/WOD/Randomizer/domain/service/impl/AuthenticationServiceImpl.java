package com.aleleone.WOD.Randomizer.domain.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.configuration.JwtService;
import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.domain.service.AuthenticationService;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AppUserRepository appUserRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationServiceImpl(AppUserRepository appUserRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
		this.appUserRepository = appUserRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
		var user = appUserRepository.findByEmail(request.email()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}
}
