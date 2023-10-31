package com.aleleone.WOD.Randomizer.domain.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.aleleone.WOD.Randomizer.domain.model.AppUser;

public interface AppUserService {

	public UserDetails loadUserByUsername(String s);
	
	public String singUpUser(AppUser appUser);
	
    public int enableAppUser(String email);
}
