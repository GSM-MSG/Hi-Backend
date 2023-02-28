package msg.team1.Hi.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.presentation.dto.request.LoginRequest;
import msg.team1.Hi.domain.auth.presentation.dto.request.SignUpRequest;
import msg.team1.Hi.domain.auth.presentation.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.auth.presentation.dto.response.NewTokenResponse;
import msg.team1.Hi.domain.auth.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final LogoutService logoutService;
    private final SignUpService signUpService;
    private final TokenReissueService tokenReissueService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest request) {
        signUpService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody LoginRequest request) {
        MemberLoginResponse response = loginService.execute(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse response = tokenReissueService.execute(token);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
        return ResponseEntity.noContent().build();
    }

}
