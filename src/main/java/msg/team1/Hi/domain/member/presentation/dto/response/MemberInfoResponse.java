package msg.team1.Hi.domain.member.presentation.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberInfoResponse {
    private final Long memberId;
    private final String email;
    private final String name;
    private final String number;
}
