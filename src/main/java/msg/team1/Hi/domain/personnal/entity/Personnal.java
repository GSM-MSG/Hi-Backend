package msg.team1.Hi.domain.personnal.entity;

import lombok.*;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "personnal")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Personnal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personnal_idx", nullable = false)
    private Integer personnal_idx;

    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;
}
