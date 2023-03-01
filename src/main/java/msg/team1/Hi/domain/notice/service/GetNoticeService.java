package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetNoticeService {

    private final NoticeRepository noticeRepository;

    public GetIdNoticeResponse execute(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));

        return GetIdNoticeResponse.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .modifiedDate(notice.getModifiedDate())
                .build();
    }
}
