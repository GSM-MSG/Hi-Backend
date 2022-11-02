package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.homebase.repository.HomeBaseRepository;
import msg.team1.Hi.domain.reservation.dto.ReservationRepDto;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final HomeBaseRepository homeBaseRepository;
    private final UserRepository userRepository;


    @Override
    public ReservationRepDto makeUserReservation(Integer user_idx, Integer homeBase_idx) {
        Optional<User> user = userRepository.findById(user_idx);
        Optional<Homebase> homeBase = homeBaseRepository.findById(homeBase_idx);

        Reservation reservation = new Reservation(user.get(), homeBase.get());
        reservationRepository.save(reservation);

        return null;
    }

}
