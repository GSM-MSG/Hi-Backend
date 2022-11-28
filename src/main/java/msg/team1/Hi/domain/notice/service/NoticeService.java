package msg.team1.Hi.domain.notice.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.dto.request.RequestNotice;
import msg.team1.Hi.domain.notice.dto.response.ResponseGetNotice;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ResponseGetNotice> getAllNotice(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);

        if(noticePage.isEmpty()) {
            throw new NoticeNotFoundException("공지사항이 존재하지 않습니다.");
        }

        return noticePage.map(notice -> {
                    ModelMapper mapper = new ModelMapper();
                    ResponseGetNotice map = mapper.map(notice , ResponseGetNotice.class);
                    map.setRole(notice.getMember().getRole());

                    return map;
                });
    }


}
