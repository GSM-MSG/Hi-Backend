package msg.team1.Hi.domain.home_base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.reservation.entity.Reservation;

import javax.persistence.*;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "home_base_id")
    private Long id;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "period")
    private Integer period;

    @Column(name = "is_available")
    private boolean isAvailable = true;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public void updateIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
