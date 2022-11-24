package msg.team1.Hi.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class ChangePasswordRequest {

    @NotBlank
    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,16}",message = "비밀번호는 특수문자 1개 이상 글자 수는 8 ~ 16자 사이여야 합니다.")
    private String password;

    @JsonCreator
    public ChangePasswordRequest(@JsonProperty("password") String password) {
        this.password = password;
    }
}
