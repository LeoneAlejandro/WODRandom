package com.aleleone.WOD.Randomizer.domain.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.service.AppUserService;
import com.aleleone.WOD.Randomizer.domain.service.ConfirmationTokenService;
import com.aleleone.WOD.Randomizer.presentation.ConfirmationToken;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

	private final static String USER_NOT_FOUND_MSG= "User with email %s not found";
	private final static int TOKEN_EXPIRATION_MINUTES = 15;

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	
	
	public AppUserServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService) {
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	public String singUpUser(AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		if (userExists) {
			throw new IllegalStateException("Email already taken");
			
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		
		appUser.setPassword(encodedPassword);
		appUserRepository.save(appUser);
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
														token,
														LocalDateTime.now(),
														LocalDateTime.now().plusMinutes(TOKEN_EXPIRATION_MINUTES),
														appUser
														);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		//TODO SEND EMAIL
		
		return token;
	}

	public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
	}

}
