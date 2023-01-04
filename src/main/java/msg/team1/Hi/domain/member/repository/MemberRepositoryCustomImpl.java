package msg.team1.Hi.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static msg.team1.Hi.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public void updateHomeBaseReserveStatus() {
        queryFactory
                .update(member)
                .where(member.reserveHomeBaseStatus.eq(true))
                .set(member.reserveHomeBaseStatus, false)
                .execute();
    }
}
