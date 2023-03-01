package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class CreateNoticeService {

    private final MemberUtil memberUtil;
    private final NoticeRepository noticeRepository;

    public void execute(NoticeRequest noticeRequest) {
        Member member = memberUtil.currentMember();
        noticeRepository.save(noticeRequest.toEntity(member));
    }
}
