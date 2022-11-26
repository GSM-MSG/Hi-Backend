package msg.team1.Hi.global.exception.collection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class TokenNotValidException extends RuntimeException{

    private final ErrorCode errorCode;

    public TokenNotValidException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_NOT_VALID;
    }
}
