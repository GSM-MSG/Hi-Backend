package msg.team1.Hi.domain.auth.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.exception.ExistEmailException;
import msg.team1.Hi.domain.auth.presentation.dto.request.SignUpRequest;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.NotVerifyEmailException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import org.springframework.security.crypto.password.PasswordEncoder;

@TransactionalService
@RequiredArgsConstructor
public class SignUpService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailAuthRepository emailAuthRepository;

    private void verifyEmail(String email) {
        if(memberRepository.existsByEmail(email))
            throw new ExistEmailException("이미 존재하는 이메일입니다.");

        EmailAuth emailAuth = emailAuthRepository.findById(email)
                .orElseThrow(() -> new NotVerifyEmailException("인증되지 않은 이메일입니다."));

        if(!emailAuth.getAuthentication()){
            throw new NotVerifyEmailException("인증되지 않은 이메일입니다.");
        }
    }

    public void execute(SignUpRequest request) {
        verifyEmail(request.getEmail());
        memberRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));
    }
}
