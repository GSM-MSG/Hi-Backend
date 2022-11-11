package msg.team1.Hi.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.global.role.Role;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "학번은 필수 입력 값입니다.")
    private String number;

    @NotEmpty(message = "역할을 지정해주십시요.")
    private String role;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .number(number)
                .role(Role.from(role))
                .build();
    }

}
