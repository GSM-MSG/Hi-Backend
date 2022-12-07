package msg.team1.Hi.domain.notice.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static msg.team1.Hi.domain.member.entity.QMember.member;
import static msg.team1.Hi.domain.notice.entity.QNotice.notice;


@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory factory;

    @Override
    public Page<Notice> getAllNoticeCreateDateDesc(Pageable pageable) {
        QueryResults<Notice> noticeQueryResults = factory
                .select(notice)
                .from(notice)
                .innerJoin(notice.member , member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.noticeId.desc())
                .fetchResults();

        List<Notice> content = noticeQueryResults.getResults();

        long total = noticeQueryResults.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
