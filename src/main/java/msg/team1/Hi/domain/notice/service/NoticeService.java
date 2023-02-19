package msg.team1.Hi.domain.notice.service;

import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;

import java.util.List;

public interface NoticeService {
    public Long createNotice(NoticeRequest noticeRequest);
    public List<GetNoticeResponse> getAllNotice();
    public GetIdNoticeResponse getNoticeById(Long noticeId);
    public void updateNotice(Long noticeId , NoticeRequest requestNotice);
    public void deleteNotice(Long boardId);
}
