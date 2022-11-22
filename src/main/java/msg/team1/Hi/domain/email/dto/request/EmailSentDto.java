package msg.team1.Hi.domain.email.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailSentDto {

    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
}