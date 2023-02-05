package msg.team1.Hi.domain.member.presentation;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.presentation.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/change-pw")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        memberService.changePassword(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }

}
