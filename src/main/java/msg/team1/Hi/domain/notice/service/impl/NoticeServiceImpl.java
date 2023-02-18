package msg.team1.Hi.domain.notice.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.Role;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.ForbiddenAccessNoticeException;
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

    private void verifyNoticeOwner(Member owner){
        Member currentMember = memberUtil.currentMember();
        if(!owner.equals(currentMember))
            throw new ForbiddenAccessNoticeException("자신이 만든 공지사항이 아니므로 접근이 제한됩니다.");
    }

    private boolean isTeacherMember(){
        Member member = memberUtil.currentMember();
        if(member.getRole() == Role.TEACHER){
            return true;
        } else {
            return false;
        }
    }

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

    public GetIdNoticeResponse getNoticeById(Integer id) {
        Notice notice = noticeRepository.findById(id)
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

        if(isTeacherMember())
            verifyNoticeOwner(notice.getMember());

        notice.updateNotice(requestNotice.getTitle(), requestNotice.getContent());
    }

    public void deleteNotice(Integer noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항이 존재하지 않습니다."));

        if(isTeacherMember())
            verifyNoticeOwner(notice.getMember());

        noticeRepository.delete(notice);
    }
}
