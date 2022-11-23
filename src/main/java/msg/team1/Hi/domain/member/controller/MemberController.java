package msg.team1.Hi.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberResponse;
import msg.team1.Hi.domain.member.service.ChangePasswordService;
import msg.team1.Hi.domain.member.service.MemberService;
import msg.team1.Hi.global.security.auth.MemberDetails;
import msg.team1.Hi.global.security.jwt.properties.dto.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ChangePasswordService changePasswordService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse login(@RequestBody @Validated LoginRequest request) throws JsonProcessingException {
        MemberResponse memberResponse = memberService.login(request);
        return memberService.createTokenByLogin(memberResponse);
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/reissue")
    public TokenResponse reissue(MemberDetails memberDetails) throws JsonProcessingException {
        return memberService.reissue(memberDetails);
    }
}
