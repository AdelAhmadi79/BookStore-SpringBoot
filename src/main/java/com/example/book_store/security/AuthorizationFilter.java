package com.example.book_store.security;

import com.example.book_store.domain.model.User;
import com.example.book_store.user.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // Token not found or invalid format

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Decode the token and retrieve claims using JwtUtil
        Claims claims = JwtUtil.decodeToken(token);

        // Check if authentication is successful (null if not)
        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = userService.loadUserByUsername(claims.getSubject());
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toString()));
        SocketPrincipal socketPrincipal = null;
        socketPrincipal = SocketPrincipal.builder()
                .name(claims.getSubject())
                .token(token)
                .build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(socketPrincipal, token, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
