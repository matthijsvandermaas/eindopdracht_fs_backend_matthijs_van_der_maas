package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.AuthenticationRequest;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.AuthenticationResponse;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.CustomeUserDetailsService;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final CustomeUserDetailsService userDetailsService;

    private final JwtUtil jwtUtl;

    public AuthenticationController(AuthenticationManager authenticationManager, CustomeUserDetailsService userDetailsService, JwtUtil jwtUtl) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtl = jwtUtl;
    }

    @GetMapping(value = "/authenticated")
    public ResponseEntity<Object> authenticated(Authentication authentication, Principal principal) {
        return ResponseEntity.ok().body(principal);
    }

    /*
    Deze methode geeft het JWT token terug */
    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        }
        catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtUtl.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
