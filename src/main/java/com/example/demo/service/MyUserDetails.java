//package com.example.demo.service;
//
//import com.example.demo.model.Role;
//import com.example.demo.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.*;
//
////import org.springframework.security.core.use
//public class MyUserDetails implements UserDetails {
//    private com.example.demo.model.User user;
//    private String password;
//    public MyUserDetails(User user) {
//        this.user = user;
//        this.password = user.getPassword();
////        System.out.println("user name =" +this.user.getName());
////        System.out.println("user password =" +this.user.getPassword());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode((this.user.getPassword()));
////        System.out.println("encode password=" + encodedPassword);
////        System.out.println(user.toString());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Role> roles = user.getRoles();
//        List<SimpleGrantedAuthority> authorites = new ArrayList<>();
////         Set<Role> roles = new HashSet<Role>();
//         for(Role role : roles){
//            authorites.add(new SimpleGrantedAuthority(role.getName()));
//         }
//        return  authorites;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.isEnabled();
//    }
//}
