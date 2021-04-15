//package com.example.demo.controller;
//
//import com.example.demo.Utils.DTOs.AuthRequest;
//import com.example.demo.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/")
//public class WelcomeController {
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @GetMapping("/")
//    public  String welcome(){
//    return "Welcome to javatechie";
//    }
//
//    @PostMapping("/authenticate")
//    public String generateToken(@RequestBody  AuthRequest authRequest) throws  Exception{
////        System.out.println("hi");
//        try {
////            System.out.println("hi");
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
//            );
//        }
//
//        catch (Exception ex){
//            throw  new Exception("invalid username");
//        }
//        String token = jwtUtil.generateToken(authRequest.getUserName());
//        System.out.println("token =" + token);
////       return jwtUtil.generateToken(authRequest.getUserName());
//        return  token;
//    }
//
//}
