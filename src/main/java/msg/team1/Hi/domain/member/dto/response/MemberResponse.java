package msg.team1.Hi.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import msg.team1.Hi.domain.member.entity.Member;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private String email;
    private String name;
    private String number;

    public static MemberResponse of(Member member){
        return new MemberResponse(member.getEmail(), member.getName(), member.getNumber());
    }
}
