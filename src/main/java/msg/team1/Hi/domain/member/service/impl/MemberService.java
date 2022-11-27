package msg.team1.Hi.domain.member.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.RefreshToken;
import msg.team1.Hi.domain.member.exception.ExistEmailException;
import msg.team1.Hi.domain.member.exception.MemberNotFoundException;
import msg.team1.Hi.domain.member.exception.MisMatchPasswordException;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.member.repository.RefreshTokenRepository;
import msg.team1.Hi.global.role.Role;
import msg.team1.Hi.global.security.jwt.TokenProvider;
import msg.team1.Hi.global.security.jwt.properties.JwtProperties;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthRepository emailAuthRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final MemberUtil memberUtil;

    @Transactional
    public MemberLoginResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenProvider.generatedAccessToken(loginRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(loginRequest.getEmail());
        RefreshToken entityToRedis = new RefreshToken(loginRequest.getEmail(), refreshToken, tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());
        refreshTokenRepository.save(entityToRedis);

        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken, jwtProperties.getAccessSecret()))
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public void signUp(SignUpRequest signUpRequest) {
        boolean isExist = memberRepository.existsByEmail(signUpRequest.getEmail());
        if(isExist) {
            throw new ExistEmailException("이미 존재하는 이메일입니다.");
        }
        EmailAuth emailAuth = emailAuthRepository.findById(signUpRequest.getEmail())
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));

        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("인증되지 않은 이메일입니다.");
        }

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .number(signUpRequest.getNumber())
                .role(Role.from(signUpRequest.getRole()))
                .build();

        memberRepository.save(member);
    }
    
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        Member member = memberUtil.currentMember();
        validateAuth(member.getEmail());
        member.updatePassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        log.info("changePassword Run!");
    }

    public void validateAuth(String email) {
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException("검증되지 않은 이메일입니다."));
        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("검증되지 않은 이메일입니다.");
        }

        log.info("validateAuth Run!!");
    }

}
