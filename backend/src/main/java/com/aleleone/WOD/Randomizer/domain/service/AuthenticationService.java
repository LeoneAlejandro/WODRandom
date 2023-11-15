package com.aleleone.WOD.Randomizer.domain.service;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.AppUserDTO;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;
import com.aleleone.WOD.Randomizer.presentation.ChangePasswordRequest;

public interface AuthenticationService {

	public AuthenticationResponse authenticate(AuthenticationRequest request);
	
	public AppUserDTO findUserDTOById(Long id);
	
    public void changePassword(ChangePasswordRequest changePasswordRequest, Long userId);
}
