package com.uygunaldim.service;

import com.uygunaldim.data.entity.RefreshToken;
import com.uygunaldim.data.entity.User;
import com.uygunaldim.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    @Value("${uygunaldim.security.token.expires-in}")
    private Long expiresIn;

    public String createRefreshToken(Long id) {
        RefreshToken token = getRefreshTokenByUser(id);
        User user = User.of(userService.getUserById(id));
        if (Objects.isNull(token)) {
            token = new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expiresIn)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    public boolean isRefreshTokenExpired(RefreshToken token) {
        return token.getExpiryDate().before(Date.from(Instant.now()));
    }

    public RefreshToken getRefreshTokenByUser(Long id) {
        return refreshTokenRepository.findByUserId(id);
    }
}
