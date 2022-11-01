package msg.team1.Hi.domain.reservation.service;

import msg.team1.Hi.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {

    List<User> makeUserReservationList(Integer user_idx);
}
