package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class UpdateReservationTeamNameService {

    private final ReservationRepository reservationRepository;
    private final MemberUtil memberUtil;

    public void execute(Long reservationId, String teamName) {
        Reservation reservation = reservationRepository.findByIdAndRepresentative(reservationId, memberUtil.currentMember())
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황 입니다."));

        reservationRepository.save(reservation.updateTeamName(teamName));
    }
}
