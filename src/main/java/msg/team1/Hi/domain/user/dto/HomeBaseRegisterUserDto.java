package msg.team1.Hi.domain.user.dto;

import lombok.*;

@ToString
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeBaseRegisterUserDto {
    private String name;
    private String number;
}
