package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.exception.ForbiddenHomeBaseReservationException;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class ExitReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    private void verifyMember(Reservation reservation, Member member){
        if(!memberRepository.existsByReservation(reservation))
            throw new ForbiddenHomeBaseReservationException("홈베이스를 예약하고 있는 상황이 아닙니다.");

        if(reservationRepository.existsByIdAndRepresentative(reservation.getId(), member))
            throw new ForbiddenHomeBaseReservationException("나갈 수 있는 자격이 없습니다.");
    }

    public void execute(Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황"));
        Member currentMember = memberUtil.currentMember();
        verifyMember(reservation, currentMember);
        memberRepository.save(currentMember.updateReservationAndUseStatus(UseStatus.AVAILABLE, null));
    }
}

