package msg.team1.Hi.domain.email.exception;

import lombok.Getter;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class AuthCodeExpiredException extends RuntimeException{
    private final ErrorCode errorCode;

    public AuthCodeExpiredException(String message) {
        super(message);
        this.errorCode = ErrorCode.EXPIRE_EMAIL_CODE;
    }
}
