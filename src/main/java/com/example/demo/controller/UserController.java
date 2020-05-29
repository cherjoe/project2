package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.User;
import com.example.demo.repository.UserRepository;


@RestController
public class UserController
{
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/createusers")
	public String createUsers()
	{
		User user1=new User( "controller1@gmail.com", "Controller1");
		userRepo.save(user1);
		
		User user2=new User( "controller2@gmail.com", "Controller2");
		userRepo.save(user2);
		
		return "Users have been created!!";
	}
	
//	@GetMapping("/users")
	@RequestMapping(value="/users", method=RequestMethod.GET, 
			produces= { MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE} )
	public List<User> getUser()
	{
		return(List<User>) userRepo.findAll();
	}

	
	@RequestMapping(value="/users1",method=RequestMethod.POST )
	public String createUser1(@RequestBody User userToBeCreated)
	{
		userRepo.save(userToBeCreated);
		
		return "User Created";
	}
	
	@RequestMapping(value="/users",method=RequestMethod.POST )
	public ResponseEntity<User> createUser2(@RequestBody User userToBeCreated)
	{
		
		User newlyCreatedUser=userRepo.save(userToBeCreated);
		
		ResponseEntity respEnt=new ResponseEntity<User>(newlyCreatedUser, HttpStatus.CREATED);
		
		return respEnt;
		
	}
	
	@RequestMapping(value="/users/{idFromHere}",method=RequestMethod.PATCH )
	public ResponseEntity<User> updateUser(@PathVariable(name="idFromHere") Long id,@RequestBody User userToBeUpdated)
	{   ResponseEntity respEnt;
		if(userRepo.existsById(id))
		{
			userToBeUpdated.setId(id);
			User updateUser=userRepo.save(userToBeUpdated);
			
			respEnt=new ResponseEntity<User>(updateUser, HttpStatus.ACCEPTED);
		}
		else
		{
			respEnt=new ResponseEntity<User>(userToBeUpdated, HttpStatus.NOT_FOUND);
			return respEnt;		
		}
		return respEnt;	
		
		    
		
		
		
	}
	
	@RequestMapping(value="/users/{idFromHere}",method=RequestMethod.DELETE )
	public ResponseEntity<User> deleteUser(@PathVariable(name="idFromHere") Long id)
	{  
		ResponseEntity respEnt;
		if(userRepo.existsById(id))
		{
		userRepo.deleteById(id);
		respEnt=new ResponseEntity<String>("Deleted", HttpStatus.OK);
		return respEnt;
		}
		else
		{
		 respEnt=new ResponseEntity<String>("Not Deleted", HttpStatus.NOT_FOUND);
		 return respEnt;
		}
		
		
		
		
	}
	
}
