package msg.team1.Hi.domain.homebase.presentation.dto.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class LookUpReservationResponse {
    private final Long reservationId;
    private final String teamName;
}
