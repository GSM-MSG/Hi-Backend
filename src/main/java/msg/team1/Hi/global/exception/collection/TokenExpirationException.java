package msg.team1.Hi.global.exception.collection;

import lombok.Getter;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class TokenExpirationException extends RuntimeException{

    private final ErrorCode errorCode;

    public TokenExpirationException(String message) {
        super(message);
        this.errorCode = ErrorCode.TOKEN_EXPIRATION;
    }
}
