package com.pms.product_management_system.controller;

import com.pms.product_management_system.model.User;
import com.pms.product_management_system.repository.UserRepository;
import com.pms.product_management_system.security.JwtUtils;
import com.pms.product_management_system.security.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authorization", description = "Authorization API")
@RequestMapping("/api/auth")
public class Auth {

    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/login")
    @Operation(summary = "Generate the JWT token")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {

        try{
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(request.getUsername());
            log.info("User details :: {}",userDetails.getUsername());

            if (passwordEncoder.matches(request.getPassword(),userDetails.getPassword())) {
                String token = jwtUtils.generateToken(request.getUsername());
                return ResponseEntity.ok(new JwtResponse(token,userDetails.getUsername(),userDetails.getAuthorities().toString()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }

        }catch (UsernameNotFoundException Ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up and generate the JWT token")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<String> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add("USER");
        } else {
            roles.addAll(strRoles);
        }

        user.setRoles(roles);
        userRepository.save(user);

        //return ResponseEntity.ok("User registered successfully!");
        return login(new LoginRequest(signUpRequest.getUsername(),signUpRequest.getPassword()));
    }
}
