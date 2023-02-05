package msg.team1.Hi.domain.home_base.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class ReservedHomeBaseException extends RuntimeException{
    private final ErrorCode errorCode;

    public ReservedHomeBaseException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_RESERVED;
    }
}
