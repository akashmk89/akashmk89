package com.example.demo.controller;

import com.example.demo.Utils.DTOs.UserAddEditDTO;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import antlr.collections.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserService userService;
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public List<UserAddEditDTO> getAllUser(){
		return (List<UserAddEditDTO>) userService.getAllUsers();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	public ResponseEntity<UserAddEditDTO> addUser(@RequestBody UserAddEditDTO userToBeAdded) throws  ResourceNotFoundException { ;
		userService.addUser(userToBeAdded);
//		Set<Role> roles =(Set<Role>) roleRepository.findAll();
//		userToBeAdded.setRoles(roles);
//		userRepository.save(userToBeAdded);
		return ResponseEntity.ok().body(userToBeAdded);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/by-id")
	  public ResponseEntity<User> getUserById(@RequestParam("id") int id)
	      throws ResourceNotFoundException {
	    User user =
	        userRepository
	            .findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
	    return ResponseEntity.ok().body(user);
	 }
}
