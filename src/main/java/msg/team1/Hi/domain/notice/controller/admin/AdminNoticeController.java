package msg.team1.Hi.domain.notice.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.dto.request.RequestNotice;
import msg.team1.Hi.domain.notice.dto.response.ResponseGetIdNotice;
import msg.team1.Hi.domain.notice.dto.response.ResponseGetNotice;
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
    public ResponseEntity<Void> createNoticeAdmin(@Valid @RequestBody RequestNotice requestNotice) {
        noticeService.createNotice(requestNotice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ResponseGetNotice>> getAllNoticeAdmin(@PageableDefault(size = 6) Pageable pageable) {
        Page<ResponseGetNotice> result = noticeService.getAllNotice(pageable);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGetIdNotice> getNoticeById(@PathVariable Long id) {
        ResponseGetIdNotice notice = noticeService.getNoticeById(id);
        return ResponseEntity.ok().body(notice);
    }

}
