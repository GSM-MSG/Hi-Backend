package msg.team1.Hi.domain.member.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.exception.collection.BadRequestException;
import msg.team1.Hi.global.role.Role;
import msg.team1.Hi.global.security.auth.MemberDetails;
import msg.team1.Hi.global.security.jwt.properties.JwtProvider;
import msg.team1.Hi.global.security.jwt.properties.dto.response.TokenResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public MemberResponse login(LoginRequest loginRequest) {
        Member member = memberRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadRequestException("아이디 혹은 비밀번호를 확인하세요."));

        // 비밀번호가 일치하는지 검증
        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }

        return MemberResponse.builder()
                .name(member.getName())
                .memberEmail(member.getMemberEmail())
                .number(member.getNumber())
                .build();
    }

    @Transactional
    public MemberResponse signUp(SignUpRequest signUpRequest) {
        boolean isExist = memberRepository.existsByEmail(signUpRequest.getMemberEmail());
        if(isExist) {
            throw new BadRequestException("이미 존재하는 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        Member signUpMember = Member.builder()
                .memberEmail(signUpRequest.getMemberEmail())
                .password(encodedPassword)
                .name(signUpRequest.getName())
                .number(signUpRequest.getNumber())
                .role(Role.from(signUpRequest.getRole()))
                .build();

        signUpMember = memberRepository.save(signUpMember);

        return MemberResponse.builder()
                .name(signUpMember.getName())
                .memberEmail(signUpMember.getMemberEmail())
                .number(signUpMember.getNumber())
                .build();
    }

    @Override
    public TokenResponse reissue(@AuthenticationPrincipal MemberDetails memberDetails) throws JsonProcessingException {
        Member member = memberDetails.getMember();
        MemberResponse memberResponse = MemberResponse.builder()
                .memberEmail(member.getMemberEmail())
                .name(member.getName())
                .number(member.getNumber())
                .build();

        return jwtProvider.reissueAtk(memberResponse);
    }
}
