package msg.team1.Hi.domain.personal.entity;

import lombok.*;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personal_idx", nullable = false)
    private Integer personalId;

    @OneToOne
    @JoinColumn(name = "email")
    private Member user;

    @OneToOne
    @JoinColumn(name = "representative_email")
    private Member representative;

    @ManyToOne
    @JoinColumn(name = "reservation_idx")
    private Reservation reservation;
}
