package com.aleleone.WOD.Randomizer.domain.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.AppUserDTO;
import com.aleleone.WOD.Randomizer.domain.security.impl.JwtServiceImpl;
import com.aleleone.WOD.Randomizer.domain.service.AuthenticationService;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationRequest;
import com.aleleone.WOD.Randomizer.presentation.AuthenticationResponse;
import com.aleleone.WOD.Randomizer.presentation.ChangePasswordRequest;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AppUserRepository appUserRepository;
	private final JwtServiceImpl jwtService;
	private final AuthenticationManager authenticationManager;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AuthenticationServiceImpl(AppUserRepository appUserRepository, JwtServiceImpl jwtService, AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.appUserRepository = appUserRepository;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
		var user = appUserRepository.findByEmail(request.email()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}

	@Override
	public AppUserDTO findUserDTOById(Long id) {
		return appUserRepository.finAppUserDTOsByUserId(id);
	}
	
	@Override
	public void changePassword(ChangePasswordRequest request, Long userId) {
		AppUser user = appUserRepository.findById(userId)
						.orElseThrow(() -> new UsernameNotFoundException("User does not exists"));
		
        // check if the current password is correct
        if (!bCryptPasswordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        
        // check if the two new passwords are the same
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }
        
        user.setPassword(bCryptPasswordEncoder.encode(request.newPassword()));
        
        appUserRepository.save(user);
		
	}	
}
