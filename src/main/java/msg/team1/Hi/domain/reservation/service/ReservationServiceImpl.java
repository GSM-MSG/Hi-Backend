package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private final ReservationRepository reservationRepository;


    @Override
    public List<User> makeUserReservationList(Integer user_idx) {
        return null;
    }
}
