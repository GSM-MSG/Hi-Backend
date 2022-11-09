package msg.team1.Hi.domain.member.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.service.MemberService;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.response.LoginResponse;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody @Validated SignUpRequest signUpRequest) {
        return memberService.signUp(signUpRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        try {
            return memberService.login(request);
        } catch (Exception e) {
            return new LoginResponse(e.getMessage());
        }
    }
}
