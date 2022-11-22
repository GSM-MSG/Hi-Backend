package msg.team1.Hi.domain.email.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.email.dto.request.EmailSentDto;
import msg.team1.Hi.domain.email.service.EmailSendService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailSendService emailSendService;

    // 이메일 전송
    @PostMapping("/send")
    public ResponseEntity<Void> authEmail(@RequestBody @Validated EmailSentDto emailSentDto) {
        emailSendService.execute(emailSentDto);
        return ResponseEntity.ok().build();
    }
}
