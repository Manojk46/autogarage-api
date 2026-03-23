package com.garage.autogarage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.garage.autogarage.entity.User;
import com.garage.autogarage.repository.UserRepository;

@Service
public class UserService {
   @Autowired
   private UserRepository repository;
   BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
   public void register(User user) {
	   user.setPassword(encoder.encode(user.getPassword()));
	   repository.save(user);
   }
}
