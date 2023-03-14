package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.exception.ForbiddenHomeBaseReservationException;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.entity.enum_type.UseStatus;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class ExitReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;


    public void execute(){
        Member currentMember = memberUtil.currentMember();



        if(currentMember.getReservation() == null)
            throw new ForbiddenHomeBaseReservationException("나갈 수 있는 자격이 없습니다.");
        memberRepository.save(currentMember.updateReservationAndUseStatus(UseStatus.AVAILABLE, null));
    }
}

