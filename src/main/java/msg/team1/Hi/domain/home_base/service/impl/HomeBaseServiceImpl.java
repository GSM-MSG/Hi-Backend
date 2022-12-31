package msg.team1.Hi.domain.home_base.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.exception.ReservedHomeBaseException;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.exception.MemberNotFoundException;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeBaseServiceImpl implements HomeBaseService {

    private final HomeBaseRepository homeBaseRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reserveHomeBase(ReserveHomeBaseRequest request) {

        verifyRepresentative(request.getRepresentativeId());
        verifyMembers(request.getMembers());

        List<Member> members = memberUtil.memberIdListToMemberList(request.getMembers());
        Member representative = memberRepository.findById(request.getRepresentativeId())
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        representative.updateReserveHomeBase();

        HomeBase homeBase = HomeBase.builder()
                .stair(request.getStair())
                .members(members)
                .representative(representative.getMemberId())
                .build();

        memberRepository.save(representative);
        homeBaseRepository.save(homeBase);
    }

    private void verifyRepresentative(Integer representativeId) {
        Member representative = memberRepository.findById(representativeId)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

        if(homeBaseRepository.existsByRepresentative(representative.getMemberId())) {
            throw new ReservedHomeBaseException("이미 홈베이스 예약을 한 유저입니다. - 팀장");
        }
    }

    private void verifyMembers(List<Integer> members) {
        for (Integer memberId : members) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));

            if(member.isReserveHomeBase())
                throw new ReservedHomeBaseException("예약자 명단 중 이미 예약된 유저가 있습니다. - 멤버들");

            member.updateReserveHomeBase();
            memberRepository.save(member);
        }
    }
}
