package msg.team1.Hi.domain.notice.service;

import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.Role;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.domain.notice.service.impl.NoticeServiceImpl;
import msg.team1.Hi.global.util.MemberUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
public class NoticeServiceTest {

    @InjectMocks
    private NoticeServiceImpl noticeService;
    @Mock
    private NoticeRepository noticeRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private MemberUtil memberUtil;

    @BeforeEach
    @DisplayName("멤버 로그인 확인 테스트")
    void currentUser(){
        //given
        Member member = Member.builder()
                .email("s22043@gsm.hs.kr")
                .password(passwordEncoder.encode("hope1234!"))
                .name("김희망")
                .number("1306")
                .role(Role.STUDENT)
                .status(UseStatus.AVAILABLE)
                .build();

        when(memberRepository.save(member)).thenReturn(member);
        memberRepository.save(member);

        System.out.println("SAVE!!");

        //when 로그인 세션 발급
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority(Role.STUDENT.name())));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        System.out.println("============");
        System.out.println(context);

        //then
        Member currentMember = memberUtil.currentMember();
        assertEquals("s22043@gsm.hs.kr", currentMember.getEmail());
    }


    @Test
    @DisplayName("공지사항 작성 테스트")
    void createNoticeTest(){
        //given
        Long noticeId = noticeService.createNotice(NoticeRequest.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .build());

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeNotFoundException("존재하지 않는 공지사항"));

        // then
        assertTrue(notice.getId() != null);
    }

    @Test
    @DisplayName("공지사항 전체 조회 테스트")
    void getNoticeTest(){
        //given
        noticeService.createNotice(NoticeRequest.builder()
                .title("테스트 제목 1")
                .content("테스트 내용 1")
                .build());

        noticeService.createNotice(NoticeRequest.builder()
                .title("테스트 제목 2")
                .content("테스트 내용 2")
                .build());

        System.out.println("======SAVE NOTICE=======");

        //when
        List<GetNoticeResponse> notices = noticeService.getAllNotice();

        //then
        assertTrue(notices.size() == 2);
    }

    @Test
    @DisplayName("공지사항 상세 조회 (id로 조회) 테스트")
    void getNoticeByIdTest(){
        Member member = memberUtil.currentMember();
        //given
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .member(member)
                .title("공지사항 상세 조회 테스트 제목")
                .content("공지사항 상세 조회 테스트 내용")
                .build());

        System.out.println(savedNotice.getId());
        System.out.println("======SAVE NOTICE=======");

        //when
        GetIdNoticeResponse findNotice = noticeService.getNoticeById(savedNotice.getId());
        System.out.println(findNotice.getNoticeId());

        //then
        assertThat(findNotice.getNoticeId()).isEqualTo(savedNotice.getId());
    }

    @Test
    @DisplayName("공지사항 수정 테스트")
    void updateNoticeTest(){
        //given
        Member member = memberUtil.currentMember();
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .member(member)
                .title("공지사항 수정 테스트 제목 수정 전")
                .content("공지사항 수정 테스트 내용 수정 전")
                .build());

        //when
        noticeService.updateNotice(savedNotice.getId() , NoticeRequest.builder()
                .title("제목 수정됨")
                .content("내용 수정됨")
                .build());

        assertThat(savedNotice.getTitle()).isEqualTo("제목 수정됨");
        assertThat(savedNotice.getContent()).isEqualTo("내용 수정됨");
    }

    @Test
    @DisplayName("공지사항 삭제 테스트")
    void deleteNoticeTest(){

        currentUser();

        //given
        Member member = memberUtil.currentMember();
        Notice savedNotice = noticeRepository.save(Notice.builder()
                .member(member)
                .title("공지사항 수정 테스트 제목 수정 전")
                .content("공지사항 수정 테스트 내용 수정 전")
                .build());

        //when
        noticeService.deleteNotice(savedNotice.getId());

        //then
        List<Notice> notices = noticeRepository.findAll();
        assertTrue(notices.size() == 0);
    }
}
