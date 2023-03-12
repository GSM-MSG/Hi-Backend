package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.presentation.dto.request.UpdateReservationMemberRequest;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;
import java.util.stream.Collectors;

@TransactionalService
@RequiredArgsConstructor
public class UpdateReservationMemberService {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    private void updatePrevMemberUseStatus(Reservation reservation) {
        memberRepository.saveAll(memberRepository.findAllByReservation(reservation).stream()
                .map(m -> m.updateReservationAndUseStatus(UseStatus.AVAILABLE, reservation))
                .collect(Collectors.toList()));
    }

    public void execute(UpdateReservationMemberRequest updateReservationRequest, Long reservationId) {

        Reservation reservation = reservationRepository.findByIdAndRepresentative(reservationId, memberUtil.currentMember())
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황 입니다."));

       updatePrevMemberUseStatus(reservation);

        List<Member> members = memberUtil.convertMemberIdListToMemberList(updateReservationRequest.getMembers());

        memberRepository.saveAll(members.stream()
                .map(m -> m.updateReservationAndUseStatus(UseStatus.INUSE, reservation))
                .collect(Collectors.toList()));
    }
}
