package com.anupam.CovidPortal.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anupam.CovidPortal.dao.repository.OtpRepo;
import com.anupam.CovidPortal.model.OtpResponseModel;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	OtpRepo otpRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		OtpResponseModel userEntity = otpRepo.findByEmail(username);

		if (userEntity == null)
			throw new UsernameNotFoundException("Username not found");

		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
				userEntity.getPassword(), new ArrayList<>());
	}

}
