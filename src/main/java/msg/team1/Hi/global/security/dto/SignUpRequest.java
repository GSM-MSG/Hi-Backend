package msg.team1.Hi.global.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.global.role.Role;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String email;
    private String password;
    private String name;
    private String number;
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
