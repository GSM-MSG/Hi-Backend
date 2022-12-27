package msg.team1.Hi.domain.notice.service;

import msg.team1.Hi.domain.notice.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.dto.response.GetNoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    public void createNotice(NoticeRequest noticeRequest);
    public Page<GetNoticeResponse> getAllNotice(Pageable pageable);
    public GetIdNoticeResponse getNoticeById(Integer noticeId);
    public void updateNotice(Integer noticeId , NoticeRequest requestNotice);
    public void deleteNotice(Integer boardId);
}
