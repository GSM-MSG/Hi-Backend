package msg.team1.Hi.domain.notice.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Void> createNoticeAdmin(@Valid @RequestBody NoticeRequest noticeRequest) {
        noticeService.createNotice(noticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<GetNoticeResponse>> getAllNoticeAdmin(@PageableDefault(size = 6) Pageable pageable) {
        Page<GetNoticeResponse> result = noticeService.getAllNotice(pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIdNoticeResponse> getNoticeById(@PathVariable Long id) {
        GetIdNoticeResponse notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNoticeAdmin(@PathVariable Long id , @Valid @RequestBody NoticeRequest noticeRequest) {
        noticeService.updateNotice(id , noticeRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoticeAdmin(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok().build();
    }

}
