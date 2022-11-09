package msg.team1.Hi.domain.user.service;

import msg.team1.Hi.global.security.dto.JwtRequest;
import msg.team1.Hi.global.security.dto.JwtResponseDto;
import msg.team1.Hi.domain.user.dto.request.SignUpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    JwtResponseDto login(JwtRequest request);
    String signUp(SignUpRequest signUpUser);
    JwtResponseDto createJwtToken(Authentication authentication);

}
