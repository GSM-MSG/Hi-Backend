package msg.team1.Hi.domain.email.presentation.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class EmailSentDto {

    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private final String email;

    @JsonCreator
    public EmailSentDto(@JsonProperty("email") String email) {
        this.email = email;
    }

}
