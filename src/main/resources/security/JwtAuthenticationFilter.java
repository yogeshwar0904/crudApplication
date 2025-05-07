//package com.example.security;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Qualifier("jwtAuthenticator")
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JWTUtil jwtUtil;
//
//    private final UserDetailsService userDetailsService;
//
//    public JwtAuthenticationFilter( JWTUtil jwtUtil, UserDetailsService userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//       String token = request.getHeader("Authorization");
//       if(token != null && token.startsWith("Bearer")){
//           token = token.substring(7);
//           String customerName = jwtUtil.extractCustomerName(token);
//           if(customerName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//               UserDetails userDetails = userDetailsService.loadUserByUsername(customerName);
//
//               if(jwtUtil.isTokenValid(token)) {
//                   UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                   authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                   SecurityContextHolder.getContext().setAuthentication(authToken);
//               }
//           }
//       }
//        filterChain.doFilter(request, response);
//    }
//}
