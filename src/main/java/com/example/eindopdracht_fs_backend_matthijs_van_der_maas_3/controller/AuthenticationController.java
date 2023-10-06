package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.AuthenticationDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authentication")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(), authenticationDto.getPassword());

        try {
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = JwtService.generateToken(userDetails);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body("Token generated");
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
