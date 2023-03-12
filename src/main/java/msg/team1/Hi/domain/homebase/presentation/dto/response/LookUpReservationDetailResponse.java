package msg.team1.Hi.domain.homebase.presentation.dto.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;

import java.util.List;

@Builder
@RequiredArgsConstructor
public class LookUpReservationDetailResponse {
    private final String teamName;
    private final MemberInfoResponse representative;
    private final List<MemberInfoResponse> members;
}
