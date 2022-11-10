package msg.team1.Hi.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subject {
    private final String email;
    private final String name;
    private final String number;
    private final String tokenType;

    public static Subject atk(String email, String name, String number) {
        return new Subject(email , name , number, "accessToken");
    }

    public static Subject rtk(String email, String name, String number) {
        return new Subject(email, name, number, "refreshToken");
    }

}
