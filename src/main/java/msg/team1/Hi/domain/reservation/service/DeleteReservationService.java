package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;

@TransactionalService
@RequiredArgsConstructor
public class DeleteReservationService {
    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationsRepository;

    public void execute(Long reservationId){
        Reservation reservation = reservationsRepository.findByIdAndRepresentative(reservationId, memberUtil.currentMember())
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황 입니다."));
        List<Member> members = memberRepository.findAllByReservation(reservation);
        memberUtil.updateAllMemberUseStatusAvailable(members);
        reservationsRepository.delete(reservation);
    }
}
