package msg.team1.Hi.domain.email.repository;

import msg.team1.Hi.domain.email.entity.EmailAuth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthRepository extends CrudRepository<EmailAuth , String> {
}
