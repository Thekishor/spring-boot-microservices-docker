package com.userservice.ServiceImpl;

import com.userservice.Model.RefreshToken;
import com.userservice.Repository.RefreshTokenRepository;
import com.userservice.Repository.UserRepo;
import com.userservice.Service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepo userRepo;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(String userEmail) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepo.findUserByEmail(userEmail))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(15 * 24 * 60 * 60 * 1000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token){
        if (token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + "Refresh token was expiration. Please make a new sign_in request");
        }
        return token;
    }
}
