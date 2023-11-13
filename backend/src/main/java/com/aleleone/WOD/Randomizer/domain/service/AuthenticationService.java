package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;

public interface AuthenticationService {

	public AuthenticationResponse authenticate(AuthenticationRequest request);
}
