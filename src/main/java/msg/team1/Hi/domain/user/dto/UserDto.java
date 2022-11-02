package msg.team1.Hi.domain.user.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String email;
    private String password;
    private String number;
    private String name;
    private String authorization;
}
