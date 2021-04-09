package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiListing;
import sun.security.acl.WorldGroupImpl;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username);
        List<SimpleGrantedAuthority> userRoles = new ArrayList<>();
        for (Role role : user.getRoles()){
            userRoles.add(new SimpleGrantedAuthority(role.getName()));
        }
        if(user == null){
            throw new UsernameNotFoundException("could not find the user");
        }
           return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), userRoles);
    }
}
