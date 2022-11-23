package msg.team1.Hi.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotBlank
    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,40}",message = "비밀번호는 특수문자 1개 이상 글자 수는 8 ~ 40자 이하여야 합니다.")
    private String password;
}
