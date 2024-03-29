package msg.team1.Hi.domain.homebase.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.entity.HomeBase;
import msg.team1.Hi.domain.homebase.exception.ForbiddenHomeBaseReservationException;
import msg.team1.Hi.domain.homebase.exception.FullHomeBaseReservationException;
import msg.team1.Hi.domain.homebase.exception.NotFoundHomeBaseException;
import msg.team1.Hi.domain.homebase.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.homebase.repository.HomeBaseRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.entity.enum_type.CheckStatus;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;

@TransactionalService
@RequiredArgsConstructor
public class ReserveHomeBaseService {

    private final MemberUtil memberUtil;
    private final HomeBaseRepository homeBaseRepository;
    private final ReservationRepository reservationRepository;


    private void isAvailableHomeBaseAndMember(HomeBase homeBase, Member member) {
        if(homeBase.isFull())
            throw new FullHomeBaseReservationException("홈베이스의 자리가 꽉차있습니다.");

        if(member.getStatus() != UseStatus.AVAILABLE)
            throw new ForbiddenHomeBaseReservationException("홈베이스에 예약 가능한 상태가 아닙니다.");
    }


    public void execute(ReserveHomeBaseRequest request) {
        Member representative = memberUtil.currentMember();
        HomeBase homeBase = homeBaseRepository.findByFloorAndPeriod(request.getFloor(), request.getPeriod())
                .orElseThrow(() -> new NotFoundHomeBaseException("홈베이스가 존재하지 않습니다."));
        List<Member> members = memberUtil.convertMemberIdListToMemberList(request.getMembers());

        isAvailableHomeBaseAndMember(homeBase, representative);

        Reservation reservation = Reservation.builder()
                .teamName(request.getTeamName())
                .representative(representative)
                .homeBase(homeBase)
                .checkStatus(CheckStatus.UNCHECKED)
                .build();

        homeBase.updateTableCount(homeBase.getTableCount() + 1);
        memberUtil.updateUseStatusInUseAndReservation(members, reservation);

        if(homeBase.getTableCount() >= 4)
            homeBase.updateIsFull(true);

        reservationRepository.save(reservation);
    }
}
