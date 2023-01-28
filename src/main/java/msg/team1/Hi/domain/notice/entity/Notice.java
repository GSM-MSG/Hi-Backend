package msg.team1.Hi.domain.notice.entity;

import lombok.*;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.global.entity.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Table(name = "notice")
@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false,columnDefinition = "TEXT")
    private String content;

    public void updateNotice(String title, String content) {
        this.title = title != null ? title : this.title;
        this.content = content != null ? content : this.content;
    }
}
