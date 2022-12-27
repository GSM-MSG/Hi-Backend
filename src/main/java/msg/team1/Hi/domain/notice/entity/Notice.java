package msg.team1.Hi.domain.notice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "notice")
@Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_idx", nullable = false)
    private Integer noticeId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx", nullable = false)
    private Member member;

    @Column(name = "notice_title", nullable = false)
    private String title;

    @Column(name = "notice_content", length = 5000, nullable = false)
    private String content;

    public void updateNotice(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
