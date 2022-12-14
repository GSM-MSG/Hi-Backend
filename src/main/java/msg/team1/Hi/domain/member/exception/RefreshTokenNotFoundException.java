package msg.team1.Hi.domain.member.exception;

import lombok.Getter;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class RefreshTokenNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public RefreshTokenNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.REFRESH_TOKEN_NOT_FOUND;
    }

}
