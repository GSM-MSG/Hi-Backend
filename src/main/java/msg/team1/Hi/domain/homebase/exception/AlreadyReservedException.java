package msg.team1.Hi.domain.homebase.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class AlreadyReservedException extends RuntimeException{
    private final ErrorCode errorCode;

    public AlreadyReservedException(String message) {
        super(message);
        this.errorCode = ErrorCode.ALREADY_RESERVED;
    }
}
