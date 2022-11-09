package msg.team1.Hi.domain.reservation.entity;

import lombok.*;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_idx", nullable = false)
    private Integer reservation_idx;

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @OneToOne
    @JoinColumn(name = "homebase_idx")
    private Homebase homebase;
}
