package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.personal.entity.Personal;
import msg.team1.Hi.domain.personal.repository.PersonalRepository;
import msg.team1.Hi.domain.reservation.dto.ReservationRepDto;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final PersonalRepository personalRepository;


    @Override
    public ReservationRepDto makeUserReservation(Integer personal_idx) {

        Optional<Personal> personal = personalRepository.findById(personal_idx);

        return new ReservationRepDto(personal.get().getUser());
    }
}
