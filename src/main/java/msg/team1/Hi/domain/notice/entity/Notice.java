package msg.team1.Hi.domain.notice.entity;

import lombok.*;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.global.entity.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false,columnDefinition = "TEXT")
    private String content;

    public Notice updateNotice(String title, String content) {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .member(this.member)
                .build();
        notice.updateId(id);
        return notice;
    }

    public void updateId(Long id){
        this.id = id;
    }
}
