package msg.team1.Hi.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.domain.member.service.MemberService;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.global.security.dto.response.TokenResponse;
import msg.team1.Hi.global.security.jwt.properties.JwtProvider;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberResponse signUp(@RequestBody @Validated SignUpRequest signUpRequest) {
        return memberService.signUp(signUpRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse login(@RequestBody @Validated LoginRequest request) throws JsonProcessingException {
        MemberResponse memberResponse = memberService.login(request);
        return jwtProvider.createTokenByLogin(memberResponse);
    }
}
