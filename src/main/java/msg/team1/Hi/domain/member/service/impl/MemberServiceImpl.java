package msg.team1.Hi.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.presentation.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.member.service.MemberService;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@TransactionalService
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberUtil memberUtil;


    private void validateAuth(String email) {
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException("검증되지 않은 이메일입니다."));
        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("검증되지 않은 이메일입니다.");
        }
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        Member member = memberUtil.currentMember();
        validateAuth(member.getEmail());
        member.updatePassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        memberRepository.save(member);
    }


}
