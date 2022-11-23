package msg.team1.Hi.domain.member.service;

import msg.team1.Hi.domain.member.dto.request.ChangePasswordRequest;

public interface ChangePasswordService {
    void execute(ChangePasswordRequest changePasswordRequest);
    Boolean validateAuth(String email);
}
