package msg.team1.Hi.global.util;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.homebase.repository.HomeBaseRepository;
import msg.team1.Hi.domain.reservation.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HomeBaseUtil {
    private final HomeBaseRepository homeBaseRepository;

    public List<LookUpReservationResponse> reservationToLookUpResponseDto(List<Reservation> reservations){
        return reservations.stream()
                .map(r -> LookUpReservationResponse.builder()
                .reservationId(r.getId()).teamName(r.getTeamName()).build())
                .collect(Collectors.toList());
    }
}
