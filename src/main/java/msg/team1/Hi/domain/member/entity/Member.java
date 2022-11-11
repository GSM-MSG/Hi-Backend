package msg.team1.Hi.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.dto.request.SignUpRequest;
import msg.team1.Hi.global.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    // 하던대로 id 쓰던가
    // uuid String

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_idx" , nullable = false)
    private Integer member_idx;

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
    private  Role role;

    public Member(SignUpRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.number = request.getNumber();
        this.role = Role.from(request.getRole());
    }

    public Member(String email, String password, String number, String name, @NotEmpty(message = "역할을 지정해주십시요.") Role role) {
        this.email = email;
        this.password = password;
        this.number = number;
        this.name = name;
        this.role = role;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
