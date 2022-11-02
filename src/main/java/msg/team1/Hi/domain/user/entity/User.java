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

    public User(String email, String password, String number, String name, String authorization) {
        this.email = email;
        this.password = password;
        this.number = number;
        this.name = name;
        this.authorization = authorization;
    }
}
