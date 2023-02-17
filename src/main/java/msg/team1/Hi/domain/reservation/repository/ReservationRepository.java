package msg.team1.Hi.domain.reservation.repository;

import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByHomeBase(HomeBase homeBase);
}
