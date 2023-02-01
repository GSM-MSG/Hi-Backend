package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {
}
