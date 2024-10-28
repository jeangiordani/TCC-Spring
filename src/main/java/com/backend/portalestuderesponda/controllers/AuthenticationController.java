package com.backend.portalestuderesponda.controllers;

import com.backend.portalestuderesponda.dtos.JwtDTO;
import com.backend.portalestuderesponda.dtos.UserDTO;
import com.backend.portalestuderesponda.dtos.UserInsertDTO;
import com.backend.portalestuderesponda.dtos.UserLoginDTO;
import com.backend.portalestuderesponda.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody UserLoginDTO user) {
        JwtDTO jwtDTO = authService.login(user);
        return ResponseEntity.ok(jwtDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserInsertDTO dto){
        UserDTO user = authService.register(dto);
        return ResponseEntity.ok(user);
    }
}
