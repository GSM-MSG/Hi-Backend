package msg.team1.Hi.domain.home_base.repository;

import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeBaseRepository extends JpaRepository<HomeBase , Long> {
    boolean existsByRepresentative(Member member);
}
