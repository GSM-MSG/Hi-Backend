package msg.team1.Hi.domain.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.homebase.entity.HomeBase;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.reservation.entity.enum_type.CheckStatus;

import javax.persistence.*;

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

    @Column(name = "check_status")
    @Enumerated(EnumType.STRING)
    private CheckStatus checkStatus;

    @ManyToOne
    @JoinColumn(name = "home_base_id")
    private HomeBase homeBase;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member representative;

    public void updateCheckStatus(CheckStatus checkStatus){
        this.checkStatus = checkStatus;
    }

    public Reservation updateTeamName(String teamName){
        Reservation reservation = Reservation.builder()
                .teamName(teamName)
                .checkStatus(this.checkStatus)
                .homeBase(this.homeBase)
                .representative(this.representative)
                .build();
        reservation.updateId(id);
        return reservation;
    }

    public void updateId(Long id){
        this.id = id;
    }
}
