package com.uygunaldim.service;

import com.uygunaldim.data.dto.request.RefreshTokenRequest;
import com.uygunaldim.data.dto.request.UserRequest;
import com.uygunaldim.data.dto.response.AuthResponse;
import com.uygunaldim.data.entity.RefreshToken;
import com.uygunaldim.data.entity.User;
import com.uygunaldim.exception.AuthorizationException;
import com.uygunaldim.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse login(UserRequest request) {
        User user = userService.findUserByUsername(request.getUsername());
        String token = generateToken(request);
        return AuthResponse.builder()
                .message("User successfully logon!")
                .userId(user.getId())
                .accessToken(token)
                .refreshToken(refreshTokenService.getRefreshTokenByUser(user.getId()).getToken())
                .expiryDate(jwtProvider.getTokenExpiryDate(token))
                .build();
    }

    public AuthResponse register(UserRequest request) {
        User user = User.of(userService.createUser(request));
        String token = generateToken(request);
        return AuthResponse.builder()
                .message("User successfully registered!")
                .userId(user.getId())
                .accessToken(token)
                .refreshToken(refreshTokenService.createRefreshToken(user.getId()))
                .expiryDate(jwtProvider.getTokenExpiryDate(token))
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken token = refreshTokenService.getRefreshTokenByUser(request.getUserId());
        if (token.getToken().equals(request.getRefreshToken()) && !refreshTokenService.isRefreshTokenExpired(token)) {
            User user = token.getUser();
            String jwtToken = jwtProvider.generateTokenByUserId(user.getId());
            return AuthResponse.builder()
                    .message("Token successfully refreshed!")
                    .userId(user.getId())
                    .accessToken(jwtToken)
                    .refreshToken(refreshTokenService.getRefreshTokenByUser(request.getUserId()).getToken())
                    .expiryDate(jwtProvider.getTokenExpiryDate(jwtToken))
                    .build();
        } else {
            throw new AuthorizationException("UYGNALDM-AUTH-401", "Refresh token is not valid!");
        }
    }

    public String getTokenExpiryDate(String token) {
        return jwtProvider.getTokenExpiryDate(token);
    }

    public boolean validateToken(String token) {
        return jwtProvider.validateToken(token);
    }

    protected String generateToken(UserRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtProvider.generateToken(auth);
    }
}
