package br.com.alessanderleite.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.alessanderleite.app.model.User;
import br.com.alessanderleite.app.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);
	
	User save(UserRegistrationDto registration);
}
