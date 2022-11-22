package msg.team1.Hi.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
