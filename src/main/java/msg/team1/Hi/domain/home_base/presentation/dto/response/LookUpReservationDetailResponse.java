package msg.team1.Hi.domain.home_base.presentation.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class LookUpReservationDetailResponse {
    private final String teamName;
    private final MemberInfoResponse representative;
    private final List<MemberInfoResponse> members;
}
