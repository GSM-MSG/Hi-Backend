package msg.team1.Hi.domain.notice.presentation.controller.student;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetIdNoticeResponse;
import msg.team1.Hi.domain.notice.presentation.dto.response.GetNoticeResponse;
import msg.team1.Hi.domain.notice.service.*;
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

    private final GetNoticeService getNoticeService;
    private final GetAllNoticeService getAllNoticeService;

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

}
