package msg.team1.Hi.domain.reservation.service;

import msg.team1.Hi.domain.reservation.dto.ReservationRepDto;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    ReservationRepDto makeUserReservation(Integer personal_idx);
}
