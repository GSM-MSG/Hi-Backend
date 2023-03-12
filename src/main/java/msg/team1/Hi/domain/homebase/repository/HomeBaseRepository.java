package msg.team1.Hi.domain.homebase.repository;

import msg.team1.Hi.domain.homebase.entity.HomeBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeBaseRepository extends JpaRepository<HomeBase , Long> {
    Optional<HomeBase> findByFloorAndPeriod(Integer floor, Integer period);
}
