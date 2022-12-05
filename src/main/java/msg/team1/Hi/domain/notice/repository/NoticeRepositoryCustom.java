package msg.team1.Hi.domain.notice.repository;


import msg.team1.Hi.domain.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> getAllNoticeCreateDateDesc(Pageable pageable);
}
