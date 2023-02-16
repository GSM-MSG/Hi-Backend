package msg.team1.Hi.domain.reservation.exception;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class NotFoundReservationException extends RuntimeException{

    private final ErrorCode errorCode;

    public NotFoundReservationException(String message){
        super(message);
        this.errorCode = ErrorCode.NOT_FOUND_RESERVATION;
    }
}
