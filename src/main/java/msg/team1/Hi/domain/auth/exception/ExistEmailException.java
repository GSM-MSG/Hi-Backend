package msg.team1.Hi.domain.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class ExistEmailException extends RuntimeException {

    private final ErrorCode errorCode;

    public ExistEmailException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_EMAIL;
    }
}
