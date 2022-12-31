package msg.team1.Hi.domain.home_base.repository;

import msg.team1.Hi.domain.home_base.entity.HomeBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeBaseRepository extends JpaRepository<HomeBase , Long> {
    boolean existsByRepresentativeName(String representativeName);
}
