package msg.team1.Hi.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
