package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken , String> {
}
