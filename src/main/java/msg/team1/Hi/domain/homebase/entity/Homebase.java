package msg.team1.Hi.domain.homebase.entity;

import lombok.*;
import msg.team1.Hi.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "entity")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Homebase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "homebase_idx", nullable = false)
    private Integer homebase_idx;

    @Column(name = "floor")
    private Integer floor;

}
