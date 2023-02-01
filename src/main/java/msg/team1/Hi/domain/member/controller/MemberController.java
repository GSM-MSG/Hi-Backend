package msg.team1.Hi.domain.member.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.dto.request.LoginRequest;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.domain.member.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.member.dto.response.NewTokenResponse;
import msg.team1.Hi.domain.member.service.MemberService;
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

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody LoginRequest request) {
        MemberLoginResponse data = memberService.login(request);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/change-pw")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        memberService.changePassword(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = memberService.tokenReissue(token);
        return ResponseEntity.ok(reIssueToken);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        memberService.logout(accessToken);
        return ResponseEntity.noContent().build();
    }

}
