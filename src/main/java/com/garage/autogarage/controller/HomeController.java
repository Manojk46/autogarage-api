package com.garage.autogarage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garage.autogarage.entity.User;
import com.garage.autogarage.service.UserService;

@RestController
//@RequestMapping("auth")
public class HomeController {
	@Autowired
	UserService service;
	
	@GetMapping("home")
	public String home() {
		return "home";
	}
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
	@PostMapping("register")
 public void register(@RequestBody User user) {
	 service.register(user);
 }

}
