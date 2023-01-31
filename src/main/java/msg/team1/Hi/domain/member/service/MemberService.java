package msg.team1.Hi.domain.member.service;

import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.member.dto.response.NewTokenResponse;

public interface MemberService {

    MemberLoginResponse login(LoginRequest loginRequest);
    void signUp(SignUpRequest signUpRequest);
    void changePassword(ChangePasswordRequest changePasswordRequest);
    NewTokenResponse tokenReissue(String requestToken);

}
