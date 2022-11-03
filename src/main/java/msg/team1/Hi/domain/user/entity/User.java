package msg.team1.Hi.domain.user.entity;

import lombok.*;
import msg.team1.Hi.global.security.dto.SignUpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_idx", nullable = false)
    private Integer user_idx;

    @Column(name = "email" , nullable = false , unique = true)
    private String email;

    @Column(name = "password", nullable = false , unique = true)
    private String password;

    @Column(name = "number", unique = true )
    private String number;

    @Column(name = "name" , unique = true)
    private String name;

    @Column(name = "authorization", nullable = false , unique = true)
    private String authorization;

    public User(SignUpRequest request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.number = request.getNumber();
        this.authorization = request.getAuthorization();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }
}
