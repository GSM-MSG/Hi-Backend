package msg.team1.Hi.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.service.ChangePasswordService;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {

    private final EmailAuthRepository emailAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberUtil memberUtil;

    @Override
    public void execute(ChangePasswordRequest changePasswordRequest) {
        Member member = memberUtil.currentMember();
        validateAuth(member.getEmail());
        member.updatePassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
    }

    public void validateAuth(String email) {
        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException("검증되지 않은 이메일입니다."));
        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("검증되지 않은 이메일입니다.");
        }
    }
}
