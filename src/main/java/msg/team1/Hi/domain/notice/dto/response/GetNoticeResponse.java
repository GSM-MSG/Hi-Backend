package msg.team1.Hi.domain.notice.dto.response;

import lombok.*;
import msg.team1.Hi.global.role.Role;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetNoticeResponse {

    private final Long noticeId;
    private final String title;
    private Role role;

    private final LocalDateTime createdDateTime;

    public void setRole(Role role) {
        this.role = role;
    }
}
