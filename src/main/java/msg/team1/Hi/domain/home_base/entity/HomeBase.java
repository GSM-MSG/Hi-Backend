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

    @Column(name = "stair")
    private String stair;

    @Column(name = "period")
    private String period;

    @Column(name = "is_available")
    private boolean isAvailable = true;


    public void updateIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
