package msg.team1.Hi.domain.member.presentation;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.presentation.dto.request.ChangePasswordRequest;
import msg.team1.Hi.domain.member.presentation.dto.request.SearchMemberRequest;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.member.service.ChangePasswordService;
import msg.team1.Hi.domain.member.service.SearchMemberService;
import msg.team1.Hi.domain.member.service.WithdrawService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final ChangePasswordService changePasswordService;
    private final WithdrawService withdrawService;
    private final SearchMemberService searchMemberService;

    @PatchMapping("/change-pw")
    public ResponseEntity<Void> changePassword(@RequestBody @Validated ChangePasswordRequest changePasswordRequest) {
        changePasswordService.execute(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<MemberInfoResponse>> searchMember(@RequestBody @Validated SearchMemberRequest searchMemberRequest) {
        List<MemberInfoResponse> responses = searchMemberService.execute(searchMemberRequest);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping
    public ResponseEntity<Void> withdraw(@RequestParam String password) {
        withdrawService.execute(password);
        return ResponseEntity.noContent().build();
    }
}
