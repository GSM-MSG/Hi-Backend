package msg.team1.Hi.domain.email.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class NotVerifyEmailException extends RuntimeException{

    private final ErrorCode errorCode;

    public NotVerifyEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_VERIFY_EMAIL;
    }

}
