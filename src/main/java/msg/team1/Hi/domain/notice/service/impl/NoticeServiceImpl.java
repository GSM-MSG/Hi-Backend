package msg.team1.Hi.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.domain.notice.service.NoticeService;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

import java.util.List;
import java.util.stream.Collectors;

@TransactionalService
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberUtil memberUtil;

    public void createNotice(NoticeRequest noticeRequest) {
        Member member = memberUtil.currentMember();
        noticeRepository.save(noticeRequest.toEntity(member));
    }

    public List<GetNoticeResponse> getAllNotice() {
        List<Notice> notices = noticeRepository.findAll();

        List<GetNoticeResponse> response = notices.stream().map(m -> new GetNoticeResponse(
                m.getId() , m.getTitle() , m.getMember().getName(), m.getCreatedDate()))
                .collect(Collectors.toList());

        return response;
    }

    public GetIdNoticeResponse getNoticeById(Integer noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));

        return GetIdNoticeResponse
                .builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .modifiedDate(notice.getModifiedDate())
                .build();
    }

    public void updateNotice(Integer noticeId , NoticeRequest requestNotice) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("존재하지 않는 공지사항"));
        notice.updateNotice(requestNotice.getTitle(), requestNotice.getContent());
    }

    public void deleteNotice(Integer noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));
        noticeRepository.delete(notice);
    }
}
