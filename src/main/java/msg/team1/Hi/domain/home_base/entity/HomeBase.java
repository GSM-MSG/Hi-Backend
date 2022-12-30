package msg.team1.Hi.domain.home_base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "home_base_id", nullable = false)
    private Long homeBaseId;

    @Column(name = "stair")
    private String stair;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private List<Member> members;

}
