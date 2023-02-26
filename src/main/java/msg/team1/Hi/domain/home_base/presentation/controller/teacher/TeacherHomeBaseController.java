package msg.team1.Hi.domain.home_base.presentation.controller.teacher;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.reservation.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher/home-base")
public class TeacherHomeBaseController {

    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<LookUpReservationResponse>> lookUpReservations(@RequestParam Integer floor,
                                                                              @RequestParam Integer period){
        List<LookUpReservationResponse> responses = reservationService.lookUpAllReservation(floor, period);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<LookUpReservationDetailResponse> lookUpReservation(@PathVariable Long reservationId){
        LookUpReservationDetailResponse response = reservationService.lookUpReservation(reservationId);
        return ResponseEntity.ok(response);
    }
}
