package msg.team1.Hi.domain.home_base.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class FullHomeBaseReservationException extends RuntimeException{
    private ErrorCode errorCode;

    public FullHomeBaseReservationException(String message) {
        super(message);
        this.errorCode = ErrorCode.HOME_BASE_FULL;
    }
}
