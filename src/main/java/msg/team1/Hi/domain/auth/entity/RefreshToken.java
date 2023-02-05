package msg.team1.Hi.domain.auth.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString @Builder
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String email;

    @Indexed
    private String token;

    @TimeToLive
    private long expiredAt;

    public void exchangeRefreshToken(String token) {
        this.token = token;
    }
}
