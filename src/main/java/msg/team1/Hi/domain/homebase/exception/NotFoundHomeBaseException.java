package msg.team1.Hi.domain.homebase.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class NotFoundHomeBaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public NotFoundHomeBaseException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_FOUND_HOME_BASE;
    }
}
