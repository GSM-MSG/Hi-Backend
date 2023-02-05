package msg.team1.Hi.domain.auth.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.entity.BlackList;
import msg.team1.Hi.domain.auth.entity.RefreshToken;
import msg.team1.Hi.domain.auth.exception.BlackListAlreadyExistException;
import msg.team1.Hi.domain.auth.exception.ExistEmailException;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.auth.exception.RefreshTokenNotFoundException;
import msg.team1.Hi.domain.auth.presentation.dto.request.LoginRequest;
import msg.team1.Hi.domain.auth.presentation.dto.request.SignUpRequest;
import msg.team1.Hi.domain.auth.presentation.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.auth.presentation.dto.response.NewTokenResponse;
import msg.team1.Hi.domain.auth.repository.BlackListRepository;
import msg.team1.Hi.domain.auth.repository.RefreshTokenRepository;
import msg.team1.Hi.domain.auth.service.AuthService;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.exception.MisMatchPasswordException;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.error.collection.TokenNotValidException;
import msg.team1.Hi.global.security.jwt.TokenProvider;
import msg.team1.Hi.global.security.jwt.properties.JwtProperties;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZonedDateTime;

@TransactionalService
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BlackListRepository blackListRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final MemberUtil memberUtil;


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

    private void verifyEmail(String email) {
        boolean isExist = memberRepository.existsByEmail(email);
        if(isExist) {
            throw new ExistEmailException("이미 존재하는 이메일입니다.");
        }
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));

        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("인증되지 않은 이메일입니다.");
        }
    }

    @Override
    public MemberLoginResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenProvider.generatedAccessToken(loginRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(loginRequest.getEmail());

        RefreshToken token = RefreshToken.builder()
                .email(loginRequest.getEmail())
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



    @Override
    public void signUp(SignUpRequest signUpRequest) {
        verifyEmail(signUpRequest.getEmail());
        memberRepository.save(signUpRequest.toEntity(passwordEncoder.encode(signUpRequest.getPassword())));
    }

    @Override
    public NewTokenResponse tokenReissue(String requestToken) {
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

    @Override
    public void logout(String accessToken) {
        Member member = memberUtil.currentMember();
        String email = tokenProvider.getUserEmail(accessToken, jwtProperties.getAccessSecret());
        RefreshToken refreshToken = refreshTokenRepository.findById(email)
                .orElseThrow(() -> new RefreshTokenNotFoundException("존재하지 않는 리프레시 토큰입니다."));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(member.getEmail(), accessToken);
    }

}