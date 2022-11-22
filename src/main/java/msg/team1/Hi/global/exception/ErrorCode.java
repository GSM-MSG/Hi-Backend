package msg.team1.Hi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번의 이메일 요청만 가능합니다." , 429),
    EMAIL_SEND_FAIL("메일 발송에 실패했습니다." , 500),
    EXPIRE_EMAIL_CODE("이메일 인증번호 시간이 만료되었습니다.", 401);
    private String message;
    private int status;
}