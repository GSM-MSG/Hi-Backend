package msg.team1.Hi.domain.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class MisMatchPasswordException extends RuntimeException{

    private final ErrorCode errorCode;

    public MisMatchPasswordException(String message) {
        super(message);
        this.errorCode = ErrorCode.MISMATCH_MEMBER_PASSWORD;
    }
}
