package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RolesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @CrossOrigin(origins = "http://localhost:4200")

    @GetMapping("/get")
    public List<Role> getAllRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/add")
    public ResponseEntity<Role> addUser(@RequestBody Role role) throws ResourceNotFoundException {
        roleRepository.save(role);
        return ResponseEntity.ok().body(role);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/by-id")
    public ResponseEntity<Role> getRoleById(@RequestParam("id") int id)
            throws ResourceNotFoundException {
        Role role =
                roleRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
        return ResponseEntity.ok().body(role);
    }
}

