package msg.team1.Hi.global.security.jwt.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.global.security.dto.response.TokenResponse;
import msg.team1.Hi.global.security.jwt.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final ObjectMapper objectMapper;

    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.live.atk}")
    private Long accessTokenLive;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponse createTokenByLogin(MemberResponse memberResponse) throws JsonProcessingException {
        Subject accessTokenSubject = Subject.atk(
                memberResponse.getEmail(),
                memberResponse.getName(),
                memberResponse.getNumber());
        String accessToken = createToken(accessTokenSubject , accessTokenLive);
        return new TokenResponse(accessToken, null);
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
