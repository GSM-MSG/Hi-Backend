package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken , String> {
    Optional<RefreshToken> findRefreshTokenByEmail(String email);
}
