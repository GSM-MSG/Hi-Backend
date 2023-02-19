package msg.team1.Hi.domain.notice.presentation.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/notice")
public class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> createNotice(@Valid @RequestBody NoticeRequest noticeRequest) {
        noticeService.createNotice(noticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetNoticeResponse>> getAllNotice() {
        List<GetNoticeResponse> result = noticeService.getAllNotice();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIdNoticeResponse> getNoticeById(@PathVariable Long id) {
        GetIdNoticeResponse notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long id , @Valid @RequestBody NoticeRequest noticeRequest) {
        noticeService.updateNotice(id , noticeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.noContent().build();
    }
}
