package msg.team1.Hi.domain.reservation.repository;

import msg.team1.Hi.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
