package com.garage.autogarage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.garage.autogarage.entity.User;
import com.garage.autogarage.principal.UserPrincipal;
import com.garage.autogarage.repository.UserRepository;
@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repository.findByName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Not Found");
		}
		return new UserPrincipal(user);
	}

}
