package msg.team1.Hi.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.enum_type.UsingStatus;
import msg.team1.Hi.global.role.Role;

import javax.persistence.*;

@Entity @Table(name = "member")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id" , nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "password", nullable = false , unique = true)
    private String password;

    @Column(name = "number", unique = true , length = 10)
    private String number;

    @Column(name = "name" , unique = true , length = 5)
    private String name;

    @Column(name = "role" , length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UsingStatus status;

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateStatus(String status) {
        this.status = UsingStatus.valueOf(status);
    }
}
