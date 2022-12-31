package msg.team1.Hi.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.exception.MemberNotFoundException;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository memberRepository;

    public Member currentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다"));
    }

    public List<Member> memberNameListToMemberList(List<String> memberNameList) {
        List<Member> members = memberNameList.stream()
                .map(n -> memberRepository.findByName(n)
                        .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 유저입니다.")))
                .collect(Collectors.toList());
        return members;
    }


}
