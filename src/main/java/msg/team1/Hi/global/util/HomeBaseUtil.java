package msg.team1.Hi.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Component
public class HomeBaseUtil {

    private final MemberRepository memberRepository;

    public void deleteMembersHomeBase(List<Member> members) {
        for (Member member : members) {
            deleteMemberHomeBase(member);
        }
    }

    public void deleteMemberHomeBase(Member member) {
        member.updateFalseReserveHomeBase();
        member.deleteHomeBase();
        memberRepository.save(member);
    }

}
