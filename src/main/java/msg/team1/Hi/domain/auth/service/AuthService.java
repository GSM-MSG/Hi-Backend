package msg.team1.Hi.domain.auth.service;

import msg.team1.Hi.domain.auth.presentation.dto.request.LoginRequest;
import msg.team1.Hi.domain.auth.presentation.dto.request.SignUpRequest;
import msg.team1.Hi.domain.auth.presentation.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.auth.presentation.dto.response.NewTokenResponse;

public interface AuthService {

    MemberLoginResponse login(LoginRequest loginRequest);
    void signUp(SignUpRequest signUpRequest);
    NewTokenResponse tokenReissue(String requestToken);
    void logout(String accessToken);
}
