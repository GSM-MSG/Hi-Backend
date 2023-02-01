package msg.team1.Hi.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash()
public class BlackList {
    @Id
    private String accessToken;
    private String email;
    @TimeToLive
    private ZonedDateTime timeToLive;
}
