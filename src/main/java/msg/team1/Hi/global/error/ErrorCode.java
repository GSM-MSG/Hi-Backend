package msg.team1.Hi.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MANY_REQUEST_EMAIL_AUTH("15분에 최대 3번의 이메일 요청만 가능합니다." , 429),
    EXPIRE_EMAIL_CODE("이메일 인증번호 시간이 만료되었습니다.", 401),
    MEMBER_NOT_FOUND("존재하지 않는 회원입니다.", 404),
    MISMATCH_AUTH_CODE("인증번호가 일치하지 않습니다." , 400),
    NOT_VERIFY_EMAIL("검증되지 않은 이메일입니다." , 401),
    MISMATCH_MEMBER_PASSWORD("비밀번호가 일치하지 않습니다.", 400),
    ALREADY_EXIST_EMAIL("이미 존재하는 이메일입니다." , 409),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),
    TOKEN_EXPIRATION("토큰이 만료 되었습니다.", 401),
    NOTICE_NOT_FOUND("공지사항이 존재하지 않습니다.", 404),
    ALREADY_RESERVED("이미 예약한 상태입니다." , 400),
    ALREADY_EXIST_REFRESH_TOKEN("이미 존재하는 리프레시 토큰입니다.", 409),
    HOME_BASE_FULL("홈베이스 자리가 꽉 차있습니다.", 406),
    NOT_FOUND_HOME_BASE("홈베이스를 찾을 수 없습니다.", 404),
    FORBIDDEN_RESERVATION("홈베이스 예약이 가능한 상태가 아닙니다.", 403),
    REFRESH_TOKEN_NOT_FOUND("존재하지 않는 리프레시 토큰입니다.", 404);


    private String message;
    private int status;
}
