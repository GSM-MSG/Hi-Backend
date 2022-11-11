package msg.team1.Hi.global.exception.collection;

import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException{
    public ForbiddenException(String message) {
        super(message);
    }
}
