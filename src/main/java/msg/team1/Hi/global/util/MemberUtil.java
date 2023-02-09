package msg.team1.Hi.global.util;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.exception.MisMatchPasswordException;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member currentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다"));
    }

    public void checkPassword(Member member,String password) {
        if(!passwordEncoder.matches(password, member.getPassword()))
            throw new MisMatchPasswordException("비밀번호가 일치하지 않음");
    }

    public List<Member> convertMemberIdListToMemberList(List<Integer> memberIdList){
        return memberIdList.stream()
                .map(m -> memberRepository.findById(m).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 멤버입니다.")))
                .collect(Collectors.toList());

    }

}
