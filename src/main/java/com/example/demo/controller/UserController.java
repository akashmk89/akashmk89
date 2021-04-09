package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;
//import antlr.collections.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userDAO;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public List<User> getAllUser(){
		return (List<User>) userDAO.findAll();
	}
	@PostMapping("/add")
	public String addUser(@RequestBody User userToBeAdded) {
		User user = new User();
//		user.name = "akash";
		userDAO.save(userToBeAdded);
		return "User";
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/by-id/{id}")
	  public ResponseEntity<User> getUserById(@PathVariable("id") int id)
	      throws ResourceNotFoundException {
	    User user =
	        userDAO
	            .findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
	    return ResponseEntity.ok().body(user);
	 }
}
