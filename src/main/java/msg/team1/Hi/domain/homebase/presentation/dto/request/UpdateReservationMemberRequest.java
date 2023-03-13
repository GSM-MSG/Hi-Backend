package msg.team1.Hi.domain.homebase.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationMemberRequest {
    private List<Long> members;
}
