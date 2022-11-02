package msg.team1.Hi.domain.reservation.service;

import msg.team1.Hi.domain.homebase.dto.HomebaseDto;
import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.reservation.dto.ReservationRepDto;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    ReservationRepDto makeUserReservation(Integer user_idx, Integer homeBase_idx);
}
