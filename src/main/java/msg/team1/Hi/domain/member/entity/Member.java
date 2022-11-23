package msg.team1.Hi.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.global.role.Role;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_idx" , nullable = false)
    private Integer memberId;

    @Column(name = "memberEmail", nullable = false, length = 20)
    private String email;

    @Column(name = "password", nullable = false , unique = true)
    private String password;

    @Column(name = "number", unique = true , length = 10)
    private String number;

    @Column(name = "name" , unique = true , length = 5)
    private String name;

    @Column(name = "role" , length = 10)
    @Enumerated(EnumType.STRING)
    private  Role role;

    public void updatePassword(String password) {
        this.password = password;
    }

}
