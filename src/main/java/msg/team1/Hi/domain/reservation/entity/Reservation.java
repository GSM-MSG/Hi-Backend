package msg.team1.Hi.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.reservation.entity.enum_type.CheckStatus;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id", nullable = false)
    private Long id;

    @Column(name = "team_name")
    private String teamName;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "id")
    private List<Member> members = new ArrayList<>();

    @Column(name = "check_status")
    @Enumerated(EnumType.STRING)
    private CheckStatus checkStatus;

    @ManyToOne
    @JoinColumn(name = "home_base_id")
    private HomeBase homeBase;

    @OneToOne(mappedBy = "reservation")
    private Member representative;

    public void updateCheckStatus(CheckStatus checkStatus){
        this.checkStatus = checkStatus;
    }
}
