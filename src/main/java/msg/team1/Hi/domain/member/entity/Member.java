package msg.team1.Hi.domain.member.entity;

import lombok.*;
import msg.team1.Hi.global.role.Role;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    // 하던대로 id 쓰던가
    // uuid String
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email", nullable = false, length = 20)
    private String email;

    @Column(name = "password", nullable = false , unique = true , length = 20)
    private String password;

    @Column(name = "number", unique = true , length = 10)
    private String number;

    @Column(name = "name" , unique = true , length = 5)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" , length = 5)
    private Role role;

    public Member(SignUpRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.number = request.getNumber();
        this.role = Role.from(request.getRole());
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
