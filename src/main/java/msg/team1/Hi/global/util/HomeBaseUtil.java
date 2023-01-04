package msg.team1.Hi.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@Component
public class HomeBaseUtil {

    private final MemberRepository memberRepository;


}
