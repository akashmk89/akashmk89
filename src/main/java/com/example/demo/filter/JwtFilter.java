//package com.example.demo.filter;
//
//import com.example.demo.JwtUtil;
//import com.example.demo.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
////important la
//
//// branch 1 commit
//
//// showing kanchana
//
//@Component
//
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsServiceImpl serviceDetailsImpl;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authorizationHeader = request.getHeader("Authorization");
//        String token = null;
//        String userName = null;
//        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer")){
//        token = authorizationHeader.substring(7);
//        userName = jwtUtil.extractUserName(token);
//            System.out.println("jwt filter line no 43");
//        }
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = serviceDetailsImpl.loadUserByUsername(userName);
//            System.out.println("jwt filter line no 47");
//            if(jwtUtil.validateToken(token, userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//           SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
