package msg.team1.Hi.domain.email.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.email.dto.request.EmailSentDto;
import msg.team1.Hi.domain.email.service.EmailCheckService;
import msg.team1.Hi.domain.email.service.EmailSendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailSendService emailSendService;
    private final EmailCheckService emailCheckService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody @Validated EmailSentDto emailSentDto) {
        emailSendService.execute(emailSentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> mailCheck(@Email @RequestParam String email, @RequestParam String authKey) {
        emailCheckService.execute(email, authKey);
        return ResponseEntity.ok().build();
    }
}
