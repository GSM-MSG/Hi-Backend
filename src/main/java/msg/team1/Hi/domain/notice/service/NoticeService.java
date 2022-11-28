package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.dto.request.RequestNotice;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;

    public void createNotice(RequestNotice requestNotice) {
        Member member = memberUtil.currentMember();
        noticeRepository.save(requestNotice.toEntity(member));
    }

}
