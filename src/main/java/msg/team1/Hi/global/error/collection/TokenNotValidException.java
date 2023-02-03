package msg.team1.Hi.global.error.collection;

import lombok.Getter;
import msg.team1.Hi.global.error.ErrorCode;

@Getter
public class TokenNotValidException extends RuntimeException{

    private final ErrorCode errorCode;

    public TokenNotValidException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_NOT_VALID;
    }
}
