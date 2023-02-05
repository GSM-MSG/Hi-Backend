package msg.team1.Hi.domain.auth.repository;

import msg.team1.Hi.domain.auth.entity.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList,String> {
}
