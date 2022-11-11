package msg.team1.Hi.global.security.jwt.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.global.common.RedisDao;
import msg.team1.Hi.global.security.jwt.properties.dto.response.TokenResponse;
import msg.team1.Hi.global.security.jwt.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final RedisDao redisDao;
    private final ObjectMapper objectMapper;

    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.live.atk}")
    private Long accessTokenLive;

    @Value("${spring.jwt.live.rtk}")
    private Long refreshTokenLive;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponse createTokenByLogin(MemberResponse memberResponse) throws JsonProcessingException {
        Subject accessTokenSubject = Subject.atk(
                memberResponse.getEmail(),
                memberResponse.getName(),
                memberResponse.getNumber());
        Subject refreshTokenSubject = Subject.rtk(
                memberResponse.getEmail(),
                memberResponse.getName(),
                memberResponse.getNumber()
        );
        String accessToken = createToken(accessTokenSubject , accessTokenLive);
        String refreshToken = createToken(refreshTokenSubject , refreshTokenLive);
        redisDao.setValues(memberResponse.getEmail(), refreshToken, Duration.ofMillis(refreshTokenLive));
        return new TokenResponse(accessToken, refreshToken);
    }

    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException{
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims().setSubject(subjectStr);
        Date date = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenLive))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Subject getSubject(String accessToken) throws JsonProcessingException {
        String subjectStr = Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }

}
