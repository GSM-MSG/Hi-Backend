package msg.team1.Hi.domain.member.service;

import msg.team1.Hi.domain.member.presentation.dto.request.ChangePasswordRequest;

public interface MemberService {

    void changePassword(ChangePasswordRequest changePasswordRequest);

}
