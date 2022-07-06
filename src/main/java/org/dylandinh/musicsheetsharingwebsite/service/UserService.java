package org.dylandinh.musicsheetsharingwebsite.service;

import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.security.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	User findByEmail(String email);
	
	User findByUsername(String username);

	User save(UserRegistrationDto registration);
}
