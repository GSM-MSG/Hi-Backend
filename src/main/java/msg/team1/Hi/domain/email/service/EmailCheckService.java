package msg.team1.Hi.domain.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.email.entity.EmailAuth;
import msg.team1.Hi.domain.email.exception.MisMatchAuthCodeException;
import msg.team1.Hi.domain.email.repository.EmailAuthRepository;
import msg.team1.Hi.domain.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
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
        log.info("run Check execute");
    }

    private void checkAuthKey(EmailAuth emailAuthEntity, String authKey) {
        if(!Objects.equals(emailAuthEntity.getRandomValue(), authKey)){
            throw new MisMatchAuthCodeException("인증번호가 일치하지 않습니다.");
        }
        emailAuthEntity.updateAuthentication(true);
        log.info("run checkAuthKey");
    }
}
