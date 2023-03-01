package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllNoticeService {

    private final NoticeRepository noticeRepository;

    public List<GetNoticeResponse> execute() {
        List<Notice> notices = noticeRepository.findAll();

        return notices.stream().map(m -> new GetNoticeResponse(
                        m.getId() , m.getTitle() , m.getMember().getName(), m.getCreatedDate()))
                .collect(Collectors.toList());
    }
}
