package msg.team1.Hi.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import msg.team1.Hi.domain.reservation.exception.NotFoundReservationException;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    public LookUpReservationDetailResponse execute(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundReservationException("존재하지 않는 예약현황입니다."));
        List<Member> reservedMembers = memberRepository.findByReservation(reservation);
        List<MemberInfoResponse> memberInfoList = memberUtil
                .memberListToDtoList(reservedMembers);

        return LookUpReservationDetailResponse.builder()
                .teamName(reservation.getTeamName())
                .representative(memberUtil.memberToDto(reservation.getRepresentative()))
                .members(memberInfoList)
                .build();
    }
}
