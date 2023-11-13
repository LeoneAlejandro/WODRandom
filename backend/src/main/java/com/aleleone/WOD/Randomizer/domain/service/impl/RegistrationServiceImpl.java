package com.aleleone.WOD.Randomizer.domain.service.impl;

import java.io.IOException;

import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;
import com.aleleone.WOD.Randomizer.domain.model.AppUser.AppUserRole;
import com.aleleone.WOD.Randomizer.domain.service.AppUserService;
import com.aleleone.WOD.Randomizer.domain.service.ConfirmationTokenService;
import com.aleleone.WOD.Randomizer.domain.service.EmailSenderService;
import com.aleleone.WOD.Randomizer.domain.service.RegistrationService;
import com.aleleone.WOD.Randomizer.presentation.ConfirmationToken;
import com.aleleone.WOD.Randomizer.presentation.RegistrationRequest;

import jakarta.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private final static String EMAIL_TEMPLATE_LOCATION = "templates/email_template.html";
	private final static String CONFRIM_TOKEN_URL = "http://localhost:8080/registration/confirm?token=";
	private final EmailValidator emailValidator;
	private final AppUserService appUserService;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSenderService emailSender;

	public RegistrationServiceImpl(EmailValidator emailValidator, AppUserService appUserService, ConfirmationTokenService confirmationTokenService, EmailSenderService emailSender) {
		this.emailValidator = emailValidator;
		this.appUserService = appUserService;
		this.confirmationTokenService = confirmationTokenService;
		this.emailSender = emailSender;
	}
	   
	
	@Override
	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("Email not valid");
		}
		String token = appUserService.singUpUser(new AppUser(request.getFirstName(),
											  				 request.getLastName(),
															 request.getEmail(), 
															 request.getPassword(),
															 AppUserRole.USER));
		
		String link = CONFRIM_TOKEN_URL + token;
		
		emailSender.send(request.getEmail(), buildEmail(request.getFirstName(), link));
		
		return token;
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
	
	
    private String loadEmailTemplate() {
        try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(EMAIL_TEMPLATE_LOCATION);
            if (inputStream == null) {
                throw new RuntimeException("Email template not found.");
            }
            String templateContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            return templateContent;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load email template.", e);
        }
    }
	
	
    private String buildEmail(String name, String link) {
        String template = loadEmailTemplate();
        template = template.replace("{{name}}", name);
        template = template.replace("{{link}}", link);
        return template;
    }

}
