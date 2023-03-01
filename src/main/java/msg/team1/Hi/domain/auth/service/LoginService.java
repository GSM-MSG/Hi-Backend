package msg.team1.Hi.domain.auth.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.entity.RefreshToken;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.auth.presentation.dto.request.LoginRequest;
import msg.team1.Hi.domain.auth.presentation.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.auth.repository.RefreshTokenRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.security.jwt.TokenProvider;
import msg.team1.Hi.global.security.jwt.properties.JwtProperties;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final MemberUtil memberUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public MemberLoginResponse execute(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        memberUtil.checkPassword(member, request.getPassword());

        String accessToken = tokenProvider.generatedAccessToken(request.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(request.getEmail());

        RefreshToken token = RefreshToken.builder()
                .email(request.getEmail())
                .token(refreshToken)
                .expiredAt(tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME())
                .build();

        refreshTokenRepository.save(token);

        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }
}
