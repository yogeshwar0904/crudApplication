package com.example.security;

import com.example.model.Customer;
import com.example.model.Role;
import com.example.repository.CustomerRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final int jwtExpirationMs = 86400000;

    private CustomerRepository customerRepository;


    public  String generateToken(String name){
        Customer customer = customerRepository.findByName(name);
        Set<Role> roles = customer.getRoles();
        return Jwts.builder().setSubject(name).claim("roles", roles.stream().
                map(role -> role.getName()).collect(Collectors.joining(",")))
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(secretKey).compact();
    }
// extract customer name
    public String extractCustomerName( String token){
        return  Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();

    }
// extract role
    public  Set<String> extractRoles(String token){
        String fetchRole = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("roles", String.class);
        return Set.of(fetchRole);
    }

  // Token validation
    public boolean isTokenValid(String token){
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }

    }
}