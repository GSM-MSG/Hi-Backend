package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.exception.NotFoundHomeBaseException;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.util.HomeBaseUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllReservationService {

    private final HomeBaseRepository homeBaseRepository;
    private final ReservationRepository reservationRepository;
    private final HomeBaseUtil homeBaseUtil;

    public List<LookUpReservationResponse> execute(Integer floor, Integer period) {
        HomeBase homeBase = homeBaseRepository.findByFloorAndPeriod(floor, period)
                .orElseThrow(() -> new NotFoundHomeBaseException("존재하지 않는 홈베이스입니다."));

        List<Reservation> reservations = reservationRepository.findAllByHomeBase(homeBase);

        return homeBaseUtil.reservationToLookUpResponseDto(reservations);
    }
}
