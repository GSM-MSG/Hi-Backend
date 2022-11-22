package msg.team1.Hi.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import msg.team1.Hi.domain.member.entity.Member;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {
    private String email;
    private String name;
    private String number;

    public MemberResponse of(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.number = member.getNumber();
        return new MemberResponse(email , name , number);
    }
}
