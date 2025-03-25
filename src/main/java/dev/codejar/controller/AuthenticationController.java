package dev.codejar.controller;


import dev.codejar.entity.UserEntity;
import dev.codejar.payload.request.LoginRequest;
import dev.codejar.payload.request.RegisterRequest;
import dev.codejar.payload.response.AuthenticationResponse;
import dev.codejar.repository.UsersRepository;
import dev.codejar.service.AuthenticationService;
import dev.codejar.service.JwtService;
import dev.codejar.service.TokenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UsersRepository usersRepository;


    @Autowired
    private TokenService tokenService;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;


    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody RegisterRequest registerRequest){
        UserEntity user = authenticationService.signup(registerRequest);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
        AuthenticationResponse saved = authenticationService.login(loginRequest);
        return ResponseEntity.ok(saved);

    }


}
