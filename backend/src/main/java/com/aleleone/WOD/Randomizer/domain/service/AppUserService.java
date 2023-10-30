package com.aleleone.WOD.Randomizer.domain.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserService {

	public UserDetails loadUserByUsername(String s);
}
