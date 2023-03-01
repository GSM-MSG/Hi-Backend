package msg.team1.Hi.domain.email.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.MisMatchAuthCodeException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@TransactionalService
@RequiredArgsConstructor
public class EmailCheckService {

    private final EmailAuthRepository emailAuthRepository;

    @Transactional(rollbackFor = Exception.class)
    public void execute(String email , String authKey) {
        EmailAuth emailAuthEntity = emailAuthRepository.findById(email)
                .orElseThrow(()-> new MemberNotFoundException("유저를 찾을 수 없습니다."));
        checkAuthKey(emailAuthEntity,authKey);
        emailAuthEntity.updateAuthentication(true);
        emailAuthRepository.save(emailAuthEntity);
    }

    private void checkAuthKey(EmailAuth emailAuthEntity, String authKey) {
        if(!Objects.equals(emailAuthEntity.getRandomValue(), authKey)){
            throw new MisMatchAuthCodeException("인증번호가 일치하지 않습니다.");
        }
    }
}
