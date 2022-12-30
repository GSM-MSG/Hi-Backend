package msg.team1.Hi.domain.notice.service;

import msg.team1.Hi.domain.notice.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.dto.response.GetNoticeResponse;

import java.util.List;

public interface NoticeService {
    public void createNotice(NoticeRequest noticeRequest);
    public List<GetNoticeResponse> getAllNotice();
    public GetIdNoticeResponse getNoticeById(Integer noticeId);
    public void updateNotice(Integer noticeId , NoticeRequest requestNotice);
    public void deleteNotice(Integer boardId);
}
