package msg.team1.Hi.domain.user.entity;

import lombok.*;

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

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "number")
    private String number;

    @Column(name = "name")
    private String name;

    @Column(name = "authorization", nullable = false)
    private String authorization;

}
