package msg.team1.Hi.domain.member.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.service.MemberService;
import msg.team1.Hi.global.security.dto.request.JwtRequest;
import msg.team1.Hi.global.security.dto.response.JwtResponse;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return memberService.signUp(signUpRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponse userLogin(@RequestBody JwtRequest request) {
        try {
            return memberService.login(request);
        } catch (Exception e) {
            return new JwtResponse(e.getMessage());
        }
    }
}
