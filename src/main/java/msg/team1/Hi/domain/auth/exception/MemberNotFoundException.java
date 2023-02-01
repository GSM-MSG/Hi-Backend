package msg.team1.Hi.domain.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class MemberNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public MemberNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.MEMBER_NOT_FOUND;
    }
}
