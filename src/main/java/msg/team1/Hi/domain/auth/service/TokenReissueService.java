package msg.team1.Hi.domain.auth.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.entity.RefreshToken;
import msg.team1.Hi.domain.auth.exception.RefreshTokenNotFoundException;
import msg.team1.Hi.domain.auth.presentation.dto.response.NewTokenResponse;
import msg.team1.Hi.domain.auth.repository.RefreshTokenRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.error.collection.TokenNotValidException;
import msg.team1.Hi.global.security.jwt.TokenProvider;
import msg.team1.Hi.global.security.jwt.properties.JwtProperties;

import java.time.ZonedDateTime;

@TransactionalService
@RequiredArgsConstructor
public class TokenReissueService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;

    public NewTokenResponse execute(String requestToken) {
        String email = tokenProvider.getUserEmail(requestToken, jwtProperties.getRefreshSecret());
        RefreshToken token = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException("리프레시 토큰이 존재하지 없습니다."));

        if(!token.getToken().equals(requestToken)) {
            throw new TokenNotValidException("검증되지 않은 토큰입니다.");
        }

        String accessToken = tokenProvider.generatedAccessToken(email);
        String refreshToken = tokenProvider.generatedRefreshToken(email);
        ZonedDateTime expiredAt = tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret());

        token.exchangeRefreshToken(refreshToken);
        refreshTokenRepository.save(token);

        return NewTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(expiredAt)
                .build();
    }
}
