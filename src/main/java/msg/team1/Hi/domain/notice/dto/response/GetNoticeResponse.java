package msg.team1.Hi.domain.notice.dto.response;

import lombok.*;
import msg.team1.Hi.global.role.Role;

import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetNoticeResponse {

    private Long noticeId;
    private String title;
    private Role role;

    private LocalDateTime createdDateTime;

    public void setRole(Role role) {
        this.role = role;
    }
}
