package msg.team1.Hi.domain.auth.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class BlackListAlreadyExistException extends RuntimeException{
    private final ErrorCode errorCode;
    public BlackListAlreadyExistException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_REFRESH_TOKEN;
    }
}
