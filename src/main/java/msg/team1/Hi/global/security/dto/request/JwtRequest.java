package msg.team1.Hi.global.security.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
