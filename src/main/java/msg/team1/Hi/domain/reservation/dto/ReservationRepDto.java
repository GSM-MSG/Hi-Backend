package msg.team1.Hi.domain.reservation.dto;

import lombok.*;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.user.entity.User;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationRepDto {

    // 대표
    private User user;

    // 등록할 홈베이스 자리
    private Homebase homebase;
}
