package msg.team1.Hi.domain.notice.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class GetNoticeResponse {

    private final Long noticeId;
    private final String title;
    private String name;

    private final LocalDateTime createdDateTime;

    public void setName(String name) {
        this.name = name;
    }
}
