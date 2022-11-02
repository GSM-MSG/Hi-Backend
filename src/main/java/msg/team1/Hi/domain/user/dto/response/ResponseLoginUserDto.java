package msg.team1.Hi.domain.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLoginUserDto {

    private String accessToken;
    private String refreshToken;
}
