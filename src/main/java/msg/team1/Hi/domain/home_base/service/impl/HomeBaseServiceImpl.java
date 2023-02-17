package msg.team1.Hi.domain.home_base.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.exception.ForbiddenHomeBaseReservationException;
import msg.team1.Hi.domain.home_base.exception.FullHomeBaseReservationException;
import msg.team1.Hi.domain.home_base.exception.NotFoundHomeBaseException;
import msg.team1.Hi.domain.home_base.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.entity.enum_type.CheckStatus;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.HomeBaseUtil;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;

@TransactionalService
@RequiredArgsConstructor
public class HomeBaseServiceImpl implements HomeBaseService {

    private final MemberUtil memberUtil;
    private final HomeBaseRepository homeBaseRepository;
    private final ReservationRepository reservationRepository;
    private final HomeBaseUtil homeBaseUtil;

    private void isAvailableHomeBaseAndMember(HomeBase homeBase, Member member) {
        if(homeBase.isFull())
            throw new FullHomeBaseReservationException("홈베이스의 자리가 꽉차있습니다.");

        if(member.getStatus() != UseStatus.AVAILABLE)
            throw new ForbiddenHomeBaseReservationException("홈베이스에 예약 가능한 상태가 아닙니다.");
    }

    private void updateMemberUseStatus(List<Member> members) {
        for (Member member : members) {
            memberUtil.updateUseStatusInUse(member);
        }
    }

    @Override
    public void reserveHomeBase(ReserveHomeBaseRequest request) {
        Member representative = memberUtil.currentMember();
        HomeBase homeBase = homeBaseRepository.findByFloorAndPeriod(request.getFloor(), request.getPeriod())
                .orElseThrow(() -> new NotFoundHomeBaseException("홈베이스가 존재하지 않습니다."));
        List<Member> members = memberUtil.convertMemberIdListToMemberList(request.getMembers());

        isAvailableHomeBaseAndMember(homeBase, representative);

        Reservation reservation = Reservation.builder()
                .teamName(request.getTeamName())
                .representative(representative)
                .members(members)
                .homeBase(homeBase)
                .checkStatus(CheckStatus.UNCHECKED)
                .build();

        homeBase.updateTableCount(homeBase.getTableCount()+1);
        updateMemberUseStatus(members);

        if(homeBase.getTableCount() >= 4)
            homeBase.updateIsFull(true);

        reservationRepository.save(reservation);
    }

    @Override
    public List<LookUpReservationResponse> lookUpAllReservation(Integer floor, Integer period) {
        HomeBase homeBase = homeBaseRepository.findByFloorAndPeriod(floor, period)
                .orElseThrow(() -> new NotFoundHomeBaseException("존재하지 않는 홈베이스입니다."));

        List<Reservation> reservations = reservationRepository.findByHomeBase(homeBase);

        if(reservations.isEmpty())
            throw new NotFoundReservationException("존재하지 않는 예약현황입니다.");

        return homeBaseUtil.reservationToLookUpResponseDto(reservations);
    }

    @Override
    public LookUpReservationDetailResponse lookUpReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황입니다."));

        List<MemberInfoResponse> memberInfoList = memberUtil.memberListToDtoList(reservation.getMembers());

        return LookUpReservationDetailResponse.builder()
                .teamName(reservation.getTeamName())
                .representative(memberUtil.memberToDto(reservation.getRepresentative()))
                .members(memberInfoList)
                .build();
    }

}
