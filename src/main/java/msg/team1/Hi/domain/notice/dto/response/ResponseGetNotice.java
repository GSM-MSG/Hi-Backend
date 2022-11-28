package msg.team1.Hi.domain.notice.dto.response;

import lombok.*;
import msg.team1.Hi.global.role.Role;

import java.time.LocalDateTime;

@Getter @Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetNotice {

    private Long noticeId;
    private String title;
    private Role role;

    private LocalDateTime createdDateTime;
}
