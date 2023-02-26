package msg.team1.Hi.domain.reservation.service;

import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;

import java.util.List;

public interface ReservationService {
    List<LookUpReservationResponse> lookUpAllReservation(Integer floor, Integer period);
    LookUpReservationDetailResponse lookUpReservation(Long reservationId);
}
