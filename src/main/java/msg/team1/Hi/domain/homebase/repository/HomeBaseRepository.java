package msg.team1.Hi.domain.homebase.repository;

import msg.team1.Hi.domain.homebase.entity.Homebase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeBaseRepository extends JpaRepository<Homebase , Integer> {
}
