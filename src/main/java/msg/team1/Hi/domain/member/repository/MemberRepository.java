package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByName(String name);
    List<Member> findAllByReservation(Reservation reservation);
    boolean existsByEmail(String email);

}
