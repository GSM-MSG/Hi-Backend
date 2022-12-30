package msg.team1.Hi.domain.home_base.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.exception.ReservedHomeBaseException;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeBaseServiceImpl implements HomeBaseService {

    private final HomeBaseRepository homeBaseRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reserveHomeBase(ReserveHomeBaseRequest request) {
        verifyMember(request);

        List<Member> members = memberUtil.memberNameListToMemberList(request.getMembers());
        Member representative = memberUtil.currentMember();
        representative.updateReserveHomeBase();

        HomeBase homeBase = HomeBase.builder()
                .stair(request.getStair())
                .members(members).
                representative(representative)
                .build();

        homeBaseRepository.save(homeBase);
    }

    private void verifyMember(ReserveHomeBaseRequest request) {
        if(homeBaseRepository.existsByRepresentative(memberRepository.findByName(request.getRepresentative()).get())) {
            throw new ReservedHomeBaseException("이미 홈베이스 예약을 한 유저입니다.");
        }

        List<String> members = request.getMembers();

        for (String name : members) {
            Optional<Member> member = memberRepository.findByName(name);
            if(member.get().isReserveHomeBase())
                throw new ReservedHomeBaseException("예약자 명단 중 이미 예약된 유저가 있습니다.");
        }
    }
}
