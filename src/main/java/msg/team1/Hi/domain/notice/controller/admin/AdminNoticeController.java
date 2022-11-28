package msg.team1.Hi.domain.notice.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.dto.request.RequestNotice;
import msg.team1.Hi.domain.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
