package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class DeleteNoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;

    public void execute(Long noticeId) {
        Notice notice = noticeRepository.findByIdAndMember(noticeId, memberUtil.currentMember())
                .orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));
        noticeRepository.delete(notice);
    }
}
