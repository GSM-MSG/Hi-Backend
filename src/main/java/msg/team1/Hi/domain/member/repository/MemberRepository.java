package msg.team1.Hi.domain.member.repository;

import msg.team1.Hi.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> , MemberRepositoryCustom {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNumber(String number);
    Optional<Member> findById(Integer memberId);
    List<Member> findAll();
    boolean existsByEmail(String email);

}
