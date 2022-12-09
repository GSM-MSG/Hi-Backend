package msg.team1.Hi.domain.email.service;

import msg.team1.Hi.domain.email.dto.request.EmailSentDto;

public interface EmailService {
    public void sendEmail(EmailSentDto emailSentDto);
    public void checkEmail(String email , String authKey);
}
