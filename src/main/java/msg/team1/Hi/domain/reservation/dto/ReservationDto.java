package msg.team1.Hi.domain.reservation.dto;

import lombok.*;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.user.entity.User;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {
    private User user;
    private Homebase homebase;
}
