package msg.team1.Hi.domain.notice.presentation.controller.teacher;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.presentation.dto.request.NoticeRequest;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher/notice")
public class TeacherNoticeController {

    private final CreateNoticeService createNoticeService;
    private final GetNoticeService getNoticeService;
    private final GetAllNoticeService getAllNoticeService;
    private final UpdateNoticeService updateNoticeService;
    private final DeleteNoticeService deleteNoticeService;

    @PostMapping
    public ResponseEntity<Void> createNotice(@Valid @RequestBody NoticeRequest noticeRequest) {
        createNoticeService.execute(noticeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetNoticeResponse>> getAllNotice() {
        List<GetNoticeResponse> responses = getAllNoticeService.execute();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetIdNoticeResponse> getNoticeById(@PathVariable Long id) {
        GetIdNoticeResponse response = getNoticeService.execute(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotice(@PathVariable Long id , @Valid @RequestBody NoticeRequest noticeRequest) {
        updateNoticeService.execute(id , noticeRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long id) {
        deleteNoticeService.execute(id);
        return ResponseEntity.noContent().build();
    }
}
