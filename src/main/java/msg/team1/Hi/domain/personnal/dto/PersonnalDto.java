package msg.team1.Hi.domain.personnal.dto;

import lombok.*;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.user.entity.User;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonnalDto {

    private User user;
    private Reservation reservation;
}
