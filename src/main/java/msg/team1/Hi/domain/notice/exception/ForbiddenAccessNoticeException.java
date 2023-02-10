package msg.team1.Hi.domain.notice.exception;

import msg.team1.Hi.global.error.ErrorCode;

public class ForbiddenAccessNoticeException extends RuntimeException{
    private final ErrorCode errorCode;

    public ForbiddenAccessNoticeException(String message){
        super(message);
        this.errorCode = ErrorCode.FORBIDDEN_NOTICE;
    }
}
