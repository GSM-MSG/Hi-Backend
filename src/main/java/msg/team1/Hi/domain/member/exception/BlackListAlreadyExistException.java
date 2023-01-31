package msg.team1.Hi.domain.member.exception;

import lombok.Getter;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class BlackListAlreadyExistException extends RuntimeException{
    private final ErrorCode errorCode;
    public BlackListAlreadyExistException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_EXIST_REFRESH_TOKEN;
    }
}
