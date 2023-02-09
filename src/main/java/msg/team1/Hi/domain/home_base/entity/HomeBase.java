package msg.team1.Hi.domain.home_base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "is_full")
    private boolean isFull = false;

    public void updateIsFull(boolean isFull) {
        this.isFull = isFull;
    }

}
