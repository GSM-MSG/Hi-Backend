package msg.team1.Hi.domain.homebase.entity;

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

    @Column(name = "table_count")
    private Integer tableCount = 0;

    public void updateIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public void updateTableCount(Integer tableCount) {
        this.tableCount = tableCount;
    }

    public void resetTableCount(){
        this.tableCount = 0;
    }

}
