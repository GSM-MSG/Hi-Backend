package msg.team1.Hi.global.util;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.exception.MemberNotFoundException;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.exception.MisMatchPasswordException;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
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

    public List<Member> convertMemberIdListToMemberList(List<Long> memberIdList){
        return memberIdList.stream()
                .map(m -> memberRepository.findById(m).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 멤버입니다.")))
                .collect(Collectors.toList());
    }

    public MemberInfoResponse memberToDto(Member member){
        return MemberInfoResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .number(member.getNumber())
                .build();
    }

    public List<MemberInfoResponse> memberListToDtoList(List<Member> members){
        return members.stream().map(m -> memberToDto(m))
                .collect(Collectors.toList());
    }

    public void updateUseStatusInUse(Member member){
        member.updateStatus(UseStatus.INUSE);
    }

    public void updateUseStatusInUseAndReservation(List<Member> members, Reservation reservation){
        memberRepository.saveAll(members.stream()
                .map(m -> m.updateReservationAndUseStatus(UseStatus.INUSE, reservation))
                .collect(Collectors.toList()));
    }

    public void updateAllMemberUseStatusAvailable(List<Member> members){
        memberRepository.saveAll(members.stream()
                .map(m -> m.updateReservationAndUseStatus(UseStatus.AVAILABLE, null))
                .collect(Collectors.toList()));
    }

}
