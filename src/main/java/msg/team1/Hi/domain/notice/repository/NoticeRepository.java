package msg.team1.Hi.domain.notice.repository;

import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice , Long>{
    Optional<Notice> findByIdAndMember(Long id, Member member);
}
