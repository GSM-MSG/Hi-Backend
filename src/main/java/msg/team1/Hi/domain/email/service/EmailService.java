package msg.team1.Hi.domain.email.service;

import msg.team1.Hi.domain.email.dto.request.EmailSentDto;

public interface EmailService {
    void sendEmail(EmailSentDto emailSentDto);
    void checkEmail(String email , String authKey);
}
