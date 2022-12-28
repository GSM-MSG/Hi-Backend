package msg.team1.Hi.domain.notice.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter @Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
public class GetNoticeResponse{

    private Integer noticeId;
    private String title;
    private String name;

    private LocalDateTime createdDateTime;
}


