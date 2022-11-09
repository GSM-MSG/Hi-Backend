package msg.team1.Hi.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class Message {
    private String message;
    private HttpStatus status;
}
