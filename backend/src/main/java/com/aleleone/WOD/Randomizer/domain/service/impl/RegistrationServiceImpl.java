package com.aleleone.WOD.Randomizer.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.AppUser.AppUserRole;
import com.aleleone.WOD.Randomizer.domain.service.AppUserService;
import com.aleleone.WOD.Randomizer.domain.service.ConfirmationTokenService;
import com.aleleone.WOD.Randomizer.domain.service.RegistrationService;
import com.aleleone.WOD.Randomizer.presentation.ConfirmationToken;
import com.aleleone.WOD.Randomizer.presentation.RegistrationRequest;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final EmailValidator emailValidator;
	private final AppUserService appUserService;
	private final ConfirmationTokenService confirmationTokenService;
	
	public RegistrationServiceImpl(EmailValidator emailValidator, AppUserService appUserService, ConfirmationTokenService confirmationTokenService) {
		this.emailValidator = emailValidator;
		this.appUserService = appUserService;
		this.confirmationTokenService = confirmationTokenService;
	}
	
	@Override
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("Email not valid");
		}
		return appUserService.singUpUser(
					new AppUser(request.getFirstName(),
								request.getLastName(),
								request.getEmail(), 
								request.getPassword(),
								AppUserRole.USER));
	}

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(()->new IllegalStateException("Token not found"));
		
		if(confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email already confirmed");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExipresAt();
		
		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token expired");
		}
		
		confirmationTokenService.setConfirmedAt(token);
		appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
		
		return "Confirmed";
	}
}
