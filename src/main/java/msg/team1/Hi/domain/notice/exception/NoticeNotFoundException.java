package msg.team1.Hi.domain.notice.exception;

import lombok.Getter;
import msg.team1.Hi.global.exception.ErrorCode;

@Getter
public class NoticeNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public NoticeNotFoundException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOTICE_NOT_FOUND;
    }
}
