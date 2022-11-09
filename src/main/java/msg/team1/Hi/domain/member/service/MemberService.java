package msg.team1.Hi.domain.member.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.global.exception.collection.BadRequestException;
import msg.team1.Hi.global.security.jwt.JwtTokenProvider;
import msg.team1.Hi.global.security.authentication.UserDetailsImpl;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.response.LoginResponse;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return createJwtToken(authentication);
    }

    @Transactional
    public MemberResponse memberLogin(LoginRequest loginRequest) {
        Member member = memberRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("아이디 혹은 비밀번호를 확인하세요."));

        boolean matches = passwordEncoder.matches(loginRequest.getPassword(), member.getPassword());
        if(!matches) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.of(member);
    }

    @Transactional
    public String signUp(SignUpRequest signUpRequest) {

        if(memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("이미 존재하는 이메일 입니다.");
        }
        Member member = new Member(signUpRequest);
        member.encryptPassword(passwordEncoder);

        memberRepository.save(member);
        return member.getEmail();
    }

    @Transactional
    public LoginResponse createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        return new LoginResponse(token);
    }
}
