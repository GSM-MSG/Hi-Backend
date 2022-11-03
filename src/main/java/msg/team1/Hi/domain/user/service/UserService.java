package msg.team1.Hi.domain.user.service;

import msg.team1.Hi.global.security.dto.JwtRequest;
import msg.team1.Hi.global.security.dto.SignUpRequest;
import msg.team1.Hi.domain.user.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public LoginResponse login(JwtRequest loginRequest);
    public String signUp(SignUpRequest signUpUser);
}
