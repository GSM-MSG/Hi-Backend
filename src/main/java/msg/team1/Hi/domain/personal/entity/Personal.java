package msg.team1.Hi.domain.personal.entity;

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
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personal_idx", nullable = false)
    private Integer personal_idx;

    @ManyToOne
    @JoinColumn(name = "reservation")
    private Reservation reservation;

    @OneToOne
    @JoinColumn(name = "user")
    private User user;
}
