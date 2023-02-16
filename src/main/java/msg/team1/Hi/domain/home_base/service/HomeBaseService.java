package msg.team1.Hi.domain.home_base.service;

import msg.team1.Hi.domain.home_base.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;

import java.util.List;

public interface HomeBaseService {
    void reserveHomeBase(ReserveHomeBaseRequest request);
    List<LookUpReservationResponse> lookUpAllReservation(Integer floor, Integer period);
}
