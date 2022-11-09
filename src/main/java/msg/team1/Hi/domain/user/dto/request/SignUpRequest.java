package msg.team1.Hi.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.global.role.Role;

import javax.validation.constraints.NotEmpty;

@Getter @Builder
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
    private Role role;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .number(number)
                .role(role)
                .build();
    }

}
