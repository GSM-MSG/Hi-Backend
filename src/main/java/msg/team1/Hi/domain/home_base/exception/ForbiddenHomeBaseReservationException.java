package msg.team1.Hi.domain.home_base.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class ForbiddenHomeBaseReservationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ForbiddenHomeBaseReservationException(String message){
        super(message);
        this.errorCode = ErrorCode.FORBIDDEN_RESERVATION;
    }

}
