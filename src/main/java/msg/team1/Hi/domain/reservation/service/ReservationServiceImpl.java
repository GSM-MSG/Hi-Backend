package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.reservation.dto.ReservationDto;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;


    @Override
    public ReservationDto makeUserReservation(User user) {

        Reservation.builder().user(user).build();
        ReservationDto reservationDto = new ReservationDto(user);

        return reservationDto;
    }
}
