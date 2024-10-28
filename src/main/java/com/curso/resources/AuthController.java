package com.curso.resources;

import com.curso.domains.dtos.CredenciaisDTO;
import com.curso.domains.dtos.TokenDTO;
import com.curso.security.JWTUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredenciaisDTO credenciais) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credenciais.getUsername(), credenciais.getPassword())
            );
            String token = jwtUtils.generateToken(credenciais.getUsername());
            return ResponseEntity.ok(new TokenDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
