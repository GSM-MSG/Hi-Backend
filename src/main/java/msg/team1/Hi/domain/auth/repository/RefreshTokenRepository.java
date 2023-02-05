package msg.team1.Hi.domain.auth.repository;

import msg.team1.Hi.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken , String> {
}
