package msg.team1.Hi.domain.home_base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "id")
    private List<Member> members;

    @OneToOne
    @JoinColumn(name = "home_base_id")
    private HomeBase homeBase;

    @OneToOne
    @JoinColumn(name = "member_id2")
    private Member member;
}
