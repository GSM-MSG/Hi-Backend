package msg.team1.Hi.domain.home_base.service.impl;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.entity.HomeBase;
import msg.team1.Hi.domain.home_base.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import msg.team1.Hi.domain.reservation.repository.ReservationRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeBaseServiceImpl implements HomeBaseService {

    private final MemberUtil memberUtil;
    private final HomeBaseRepository homeBaseRepository;
    private final ReservationRepository reservationRepository;

    // 홈베이스에 자리가 남아있는지 검증
    private void isAvailableHomeBase(HomeBase homeBase) {
        if(!homeBase.isAvailable())
            tho


    }

    @Override
    public void reserveHomeBase(ReserveHomeBaseRequest request) {

    }
}
