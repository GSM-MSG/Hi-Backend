package msg.team1.Hi.domain.notice.service;

import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.Role;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.notice.entity.Notice;
import msg.team1.Hi.domain.notice.exception.NoticeNotFoundException;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.repository.NoticeRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
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
        noticeService.createNotice(NoticeRequest.builder()
                .title("테스트를위한하나뿐인제목")
                .content("공지사항 내용")
                .build());

        Notice notice = noticeRepository.findByTitle("테스트를위한하나뿐인제목")
                .orElseThrow(() -> new NoticeNotFoundException("존재하지 않는 공지사항"));

        // then
        assertTrue(notice.getId() != null);
    }

}
