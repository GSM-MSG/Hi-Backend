package msg.team1.Hi.domain.reservation.dto;

import lombok.*;
import msg.team1.Hi.domain.homebase.entity.HomeBase;
import msg.team1.Hi.domain.member.entity.Member;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationRepDto {

    // 대표
    private Member user;

    // 등록할 홈베이스 자리
    private HomeBase homebase;
}
