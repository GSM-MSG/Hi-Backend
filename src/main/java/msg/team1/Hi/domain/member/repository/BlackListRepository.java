package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList,String> {
}
