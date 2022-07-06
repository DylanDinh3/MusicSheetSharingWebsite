package org.dylandinh.musicsheetsharingwebsite.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.dylandinh.musicsheetsharingwebsite.model.Role;
import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.repository.RoleRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.security.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setUsername(registration.getUsername());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		if(userRepository.findAll() == null)
			user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		else {
			Role role = roleRepository.findByName("ROLE_USER");
			user.setRoles(Arrays.asList(role));
		}
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UsernameNotFoundException("Invalid email or password.");
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
		user.getPassword(),
		mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}