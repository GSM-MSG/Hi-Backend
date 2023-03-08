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

@TransactionalService
@RequiredArgsConstructor
public class UpdateReservationMemberService {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;

    public void updatePrevMemberUseStatus(Reservation reservation) {
        List<Member> prevMembers = memberRepository.findAllByReservation(reservation);
        for (Member member : prevMembers) {
            member.updateStatus(UseStatus.AVAILABLE);
            member.updateReservation(null);
        }
        memberRepository.saveAll(prevMembers);
    }

    public void execute(UpdateReservationMemberRequest updateReservationRequest, Long reservationId) {

        Reservation reservation = reservationRepository.findByIdAndRepresentative(reservationId, memberUtil.currentMember())
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황 입니다."));

       updatePrevMemberUseStatus(reservation);

        List<Member> members = memberUtil.convertMemberIdListToMemberList(updateReservationRequest.getMembers());
        members.forEach(m -> m.updateReservation(reservation));
        members.forEach(m -> m.updateStatus(UseStatus.INUSE));

        memberRepository.saveAll(members);
    }
}
