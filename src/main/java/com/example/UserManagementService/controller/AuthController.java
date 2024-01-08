package com.example.UserManagementService.controller;

import com.example.UserManagementService.dto.*;
import com.example.UserManagementService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        UserDTO userDTO = authService.signUpUser(signUpRequestDTO.getName(), signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return authService.loginUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout (@RequestBody LogoutRequestDTO logoutRequestDTO){
        authService.logoutUser(logoutRequestDTO.getToken());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate")
    public ResponseEntity<SessionStatusDTO> validateSession(@RequestBody validateSessionRequestDTO validateSessionRequestDTO){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
