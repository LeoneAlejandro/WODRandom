package com.aleleone.WOD.Randomizer.domain.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aleleone.WOD.Randomizer.datasource.repository.AppUserRepository;
import com.aleleone.WOD.Randomizer.domain.service.AppUserService;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

	private final static String USER_NOT_FOUND_MSG= "User with email %s not found";
	private final AppUserRepository appUserRepository;
	
	public AppUserServiceImpl(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return appUserRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}

}
