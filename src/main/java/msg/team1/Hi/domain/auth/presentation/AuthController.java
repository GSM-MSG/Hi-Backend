package msg.team1.Hi.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.presentation.dto.request.LoginRequest;
import msg.team1.Hi.domain.auth.presentation.dto.request.SignUpRequest;
import msg.team1.Hi.domain.auth.presentation.dto.response.MemberLoginResponse;
import msg.team1.Hi.domain.auth.presentation.dto.response.NewTokenResponse;
import msg.team1.Hi.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> signUp(@RequestBody @Validated SignUpRequest signUpRequest) {
        authService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody LoginRequest request) {
        MemberLoginResponse data = authService.login(request);
        return ResponseEntity.ok(data);
    }

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("RefreshToken") String token) {
        NewTokenResponse reIssueToken = authService.tokenReissue(token);
        return ResponseEntity.ok(reIssueToken);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
        return ResponseEntity.noContent().build();
    }

}
