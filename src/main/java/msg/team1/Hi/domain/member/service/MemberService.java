package msg.team1.Hi.domain.member.service;

import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.member.dto.response.NewTokenResponse;

public interface MemberService {

    public MemberLoginResponse login(LoginRequest loginRequest);
    public void signUp(SignUpRequest signUpRequest);
    public void changePassword(ChangePasswordRequest changePasswordRequest);
    public NewTokenResponse tokenReissue(String requestToken);
}
