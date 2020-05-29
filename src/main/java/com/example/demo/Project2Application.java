package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.modules.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class Project2Application {

	public static void main(String[] args) {
		SpringApplication.run(Project2Application.class, args);
	}

	
	
	@Autowired
	UserRepository userRepo;
	
	@PostConstruct
	public void postConsructMethod()
	{
		User user1=new User( "neha@gmail.com", "Neha");
		userRepo.save(user1);
		
		User user2=new User( "gina@gmail.com", "Gina");
		userRepo.save(user2);
	}
}
