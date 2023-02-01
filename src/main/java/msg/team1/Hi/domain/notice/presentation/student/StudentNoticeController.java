package msg.team1.Hi.domain.notice.presentation.student;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.NoticeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/notice")
public class StudentNoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<List<GetNoticeResponse>> getAllNotice() {
        List<GetNoticeResponse> result = noticeService.getAllNotice();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIdNoticeResponse> getNoticeById(@PathVariable Integer id) {
        GetIdNoticeResponse notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }
}
