package com.aleleone.WOD.Randomizer.domain.service;

import java.util.Optional;

import com.aleleone.WOD.Randomizer.presentation.ConfirmationToken;

public interface ConfirmationTokenService {

	public void saveConfirmationToken(ConfirmationToken token);
	
	public Optional<ConfirmationToken> getToken(String token);
	
	public int setConfirmedAt(String token);
}
