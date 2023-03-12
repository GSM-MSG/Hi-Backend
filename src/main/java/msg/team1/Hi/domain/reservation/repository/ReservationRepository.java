package msg.team1.Hi.domain.reservation.repository;

import msg.team1.Hi.domain.homebase.entity.HomeBase;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByHomeBase(HomeBase homeBase);
    Optional<Reservation> findByIdAndRepresentative(Long id, Member representative);
}
