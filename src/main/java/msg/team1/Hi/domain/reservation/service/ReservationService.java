package msg.team1.Hi.domain.reservation.service;

import msg.team1.Hi.domain.user.entity.User;

import java.util.List;

public interface ReservationService {

    List<User> makeUserReservationList(Integer user_idx);
}
