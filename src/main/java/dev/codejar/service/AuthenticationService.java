package dev.codejar.service;

import dev.codejar.entity.UserEntity;
import dev.codejar.payload.request.LoginRequest;
import dev.codejar.payload.request.RegisterRequest;
import dev.codejar.payload.response.AuthenticationResponse;
import dev.codejar.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final JwtService jwtService;

    public AuthenticationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
    }


    public UserEntity signup(RegisterRequest input){

        UserEntity user = new UserEntity();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return usersRepository.save(user);
    }


    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        var user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        tokenService.saveUserTokens(user, accessToken, refreshToken, true);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
