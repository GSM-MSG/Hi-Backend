package msg.team1.Hi.domain.reservation.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.exception.ForbiddenHomeBaseReservationException;
import msg.team1.Hi.domain.home_base.exception.NotFoundHomeBaseException;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.domain.reservation.service.ReservationService;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.HomeBaseUtil;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;

@TransactionalService
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final MemberUtil memberUtil;
    private final HomeBaseRepository homeBaseRepository;
    private final ReservationRepository reservationRepository;
    private final HomeBaseUtil homeBaseUtil;
    private final MemberRepository memberRepository;

    @Override
    public List<LookUpReservationResponse> lookUpAllReservation(Integer floor, Integer period) {
        HomeBase homeBase = homeBaseRepository.findByFloorAndPeriod(floor, period)
                .orElseThrow(() -> new NotFoundHomeBaseException("존재하지 않는 홈베이스입니다."));

        List<Reservation> reservations = reservationRepository.findAllByHomeBase(homeBase);

        return homeBaseUtil.reservationToLookUpResponseDto(reservations);
    }

    @Override
    public LookUpReservationDetailResponse lookUpReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황입니다."));
        List<Member> reservedMembers = memberRepository.findByReservation(reservation);
        List<MemberInfoResponse> memberInfoList = memberUtil
                .memberListToDtoList(reservedMembers);

        return LookUpReservationDetailResponse.builder()
                .teamName(reservation.getTeamName())
                .representative(memberUtil.memberToDto(reservation.getRepresentative()))
                .members(memberInfoList)
                .build();
    }

    @Override
    public void updateReservationTeamName(Long reservationId, String teamName) {
        Member currentMember = memberUtil.currentMember();
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황 입니다."));

        if(!reservation.getRepresentative().equals(currentMember))
            throw new ForbiddenHomeBaseReservationException("수정할 수 있는 권한이 없는 멤버입니다.");

        reservation.updateTeamName(teamName);
        reservationRepository.save(reservation);
    }

}
