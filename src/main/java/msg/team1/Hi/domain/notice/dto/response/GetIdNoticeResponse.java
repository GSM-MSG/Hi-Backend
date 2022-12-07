package msg.team1.Hi.domain.notice.dto.response;

import lombok.*;
import msg.team1.Hi.global.role.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetIdNoticeResponse {

    private final Long id;
    private final String title;
    private final String content;
    private Role role;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public void setRole(Role role) {
        this.role = role;
    }
}
