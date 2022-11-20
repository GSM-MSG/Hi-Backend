package msg.team1.Hi.domain.reservation.entity;

import lombok.*;
import msg.team1.Hi.domain.homebase.entity.HomeBase;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_idx", nullable = false)
    private Integer reservationId;

    @OneToOne
    @JoinColumn(name = "email")
    private Member user;

    @OneToOne
    @JoinColumn(name = "homebase_idx")
    private HomeBase homebase;
}
