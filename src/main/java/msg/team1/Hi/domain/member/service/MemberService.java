package msg.team1.Hi.domain.member.service;

import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;

public interface MemberService {
    MemberResponse login(LoginRequest loginRequest);
    MemberResponse signUp(SignUpRequest signUpRequest);
}
