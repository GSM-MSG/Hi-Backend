package msg.team1.Hi.domain.reservation.dto;

import lombok.*;
import msg.team1.Hi.domain.user.entity.User;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationRepDto {

    // 대표
    private User user;
}
