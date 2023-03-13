package msg.team1.Hi.domain.member.presentation;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.presentation.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.service.ChangePasswordService;
import msg.team1.Hi.domain.member.service.WithdrawService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ChangePasswordService changePasswordService;
    private final WithdrawService withdrawService;

    @PatchMapping("/change-pw")
    public ResponseEntity<Void> execute(@RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> withdraw(@RequestParam String password) {
        withdrawService.execute(password);
        return ResponseEntity.noContent().build();
    }
}
