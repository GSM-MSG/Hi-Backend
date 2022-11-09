package msg.team1.Hi.domain.user.entity;

import lombok.*;
import msg.team1.Hi.global.role.Role;
import msg.team1.Hi.domain.user.dto.request.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false , unique = true)
    private String password;

    @Column(name = "number", unique = true )
    private String number;

    @Column(name = "name" , unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(SignUpRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.number = request.getNumber();
        this.role = Role.STUDENT;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
