package com.aleleone.WOD.Randomizer.domain.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.ConfirmationTokenRepository;
import com.aleleone.WOD.Randomizer.domain.service.ConfirmationTokenService;
import com.aleleone.WOD.Randomizer.presentation.ConfirmationToken;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {


	private final ConfirmationTokenRepository confirmationTokenRepository;

	public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
	}

	public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
