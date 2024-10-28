package com.backend.portalestuderesponda.services;

import com.backend.portalestuderesponda.dtos.JwtDTO;
import com.backend.portalestuderesponda.dtos.UserDTO;
import com.backend.portalestuderesponda.dtos.UserInsertDTO;
import com.backend.portalestuderesponda.dtos.UserLoginDTO;
import com.backend.portalestuderesponda.entities.Role;
import com.backend.portalestuderesponda.entities.User;
import com.backend.portalestuderesponda.exceptions.DataExistsException;
import com.backend.portalestuderesponda.exceptions.NotFoundException;
import com.backend.portalestuderesponda.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO register(UserInsertDTO dto) throws DataExistsException {
        boolean userExists = userRepository.existsByEmail(dto.getEmail());
        if(userExists){
            throw new DataExistsException("Usuário já existe");
        }

        User user = userToUserDTO(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);

        return new UserDTO(user);
    }

    public JwtDTO login(UserLoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );

        var jwt = jwtService.generateToken(user);
        return new JwtDTO(jwt);
    }

    private User userToUserDTO(UserInsertDTO dto){
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                Role.STUDENT);
    }
}
