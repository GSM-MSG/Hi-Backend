package msg.team1.Hi.domain.auth.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.entity.BlackList;
import msg.team1.Hi.domain.auth.entity.RefreshToken;
import msg.team1.Hi.domain.auth.exception.BlackListAlreadyExistException;
import msg.team1.Hi.domain.auth.exception.RefreshTokenNotFoundException;
import msg.team1.Hi.domain.auth.repository.BlackListRepository;
import msg.team1.Hi.domain.auth.repository.RefreshTokenRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.security.jwt.TokenProvider;
import msg.team1.Hi.global.security.jwt.properties.JwtProperties;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class LogoutService {

    private final BlackListRepository blackListRepository;
    private final TokenProvider tokenProvider;
    private final MemberUtil memberUtil;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    private void saveBlackList(String email, String accessToken) {

        if(blackListRepository.existsById(accessToken))
            throw new BlackListAlreadyExistException("블랙리스트에 이미 등록되어있습니다.");

        long expiredTime = tokenProvider.getACCESS_TOKEN_EXPIRE_TIME();

        BlackList blackList = BlackList.builder()
                .email(email)
                .accessToken(accessToken)
                .timeToLive(expiredTime)
                .build();

        blackListRepository.save(blackList);
    }

    public void execute(String accessToken) {
        Member member = memberUtil.currentMember();
        String email = tokenProvider.getUserEmail(accessToken, jwtProperties.getAccessSecret());
        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException("존재하지 않는 리프레시 토큰입니다."));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(member.getEmail(), accessToken);
    }
}
