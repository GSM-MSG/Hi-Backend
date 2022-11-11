package msg.team1.Hi.domain.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.global.security.auth.MemberDetails;
import msg.team1.Hi.global.security.jwt.properties.dto.response.TokenResponse;

public interface MemberService {
    MemberResponse login(LoginRequest loginRequest);
    MemberResponse signUp(SignUpRequest signUpRequest);
    TokenResponse reissue(MemberDetails memberDetails) throws JsonProcessingException;
}
