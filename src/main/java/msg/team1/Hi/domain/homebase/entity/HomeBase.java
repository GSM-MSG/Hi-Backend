package msg.team1.Hi.domain.homebase.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "homebase_idx", nullable = false)
    private Integer homebase_idx;

    @Column(name = "floor")
    private Integer floor;

}
