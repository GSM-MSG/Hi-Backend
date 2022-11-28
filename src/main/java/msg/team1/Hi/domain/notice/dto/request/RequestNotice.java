package msg.team1.Hi.domain.notice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.notice.entity.Notice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestNotice {

    @NotBlank
    @Size(min = 1, max = 45)
    private String title;

    @NotBlank
    @Size(min = 1, max = 5000)
    private String content;

    public Notice toEntity(Member member) {
        Notice board = Notice.builder()
                .title(title)
                .content(content)
                .member(member)
                .build();

        return board;
    }
}
