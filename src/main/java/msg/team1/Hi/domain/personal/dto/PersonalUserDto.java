package msg.team1.Hi.domain.personal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalUserDto {
    private String name;
    private String number;
    private String authorizations;
}
