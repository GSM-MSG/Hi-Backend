package msg.team1.Hi.domain.personal.entity;

import lombok.*;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.user.entity.User;

import javax.persistence.*;
import java.util.Optional;

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

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToOne
    @JoinColumn(name = "email2")
    private User representative;

    @ManyToOne
    @JoinColumn(name = "reservation_idx")
    private Reservation reservation;
}
