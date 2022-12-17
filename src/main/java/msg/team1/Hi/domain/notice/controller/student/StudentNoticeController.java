package msg.team1.Hi.domain.notice.controller.student;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/notice")
@RequiredArgsConstructor
public class StudentNoticeController {

    private final NoticeService noticeService;


    @GetMapping
    public ResponseEntity<Page<GetNoticeResponse>> getAllNoticeStudent(@PageableDefault(size = 6) Pageable pageable) {
        Page<GetNoticeResponse> result = noticeService.getAllNotice(pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIdNoticeResponse> getNoticeById(@PathVariable Long id) {
        GetIdNoticeResponse notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }
}
