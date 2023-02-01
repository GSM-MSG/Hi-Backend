package msg.team1.Hi.domain.notice.presentation.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class GetNoticeResponse{

    private Integer noticeId;
    private String title;
    private String name;

    private LocalDateTime createdDateTime;
}


