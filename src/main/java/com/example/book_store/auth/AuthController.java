package com.example.book_store.auth;


import com.example.book_store.security.CustomUserDetailsService;
import com.example.book_store.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    // Retrieve user details using the CustomUserDetailsService
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService; // Injected CustomUserDetailsService

    private final BCryptPasswordEncoder passwordEncoder; // Injected BCryptPasswordEncoder



    @PostMapping("api/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getUsername());


        // Verify the provided password against the stored hashed password using the BCryptPasswordEncoder
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            // Passwords do not match, return an error response or throw an exception
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return ResponseEntity.ok(LoginSrv.builder()
                .accessToken(jwtUtil.createAccessToken(authentication))
                .refreshToken(jwtUtil.createRefreshToken(authentication))
                .build());
    }


}
