package msg.team1.Hi.domain.notice.repository;

import msg.team1.Hi.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice , Long> {
}
