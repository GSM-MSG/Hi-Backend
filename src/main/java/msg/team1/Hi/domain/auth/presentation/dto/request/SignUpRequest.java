package msg.team1.Hi.domain.auth.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.Role;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 특수문자 1개 이상 글자 수는 8 ~ 16자 사이여야 합니다.")
    private String password;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "학번은 필수 입력 값입니다.")
    private String number;

    public Member toEntity(String password) {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .number(number)
                .role(Role.STUDENT)
                .status(UseStatus.AVAILABLE)
                .build();
    }

}
