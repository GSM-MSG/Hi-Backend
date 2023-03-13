package msg.team1.Hi.domain.homebase.presentation.controller.teacher;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.homebase.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.reservation.service.GetAllReservationService;
import msg.team1.Hi.domain.reservation.service.GetReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher/home-base")
public class TeacherHomeBaseController {

    private final GetAllReservationService getAllReservationService;
    private final GetReservationService getReservationService;

    @GetMapping
    public ResponseEntity<List<LookUpReservationResponse>> lookUpReservations(@RequestParam Integer floor,
                                                                              @RequestParam Integer period){
        List<LookUpReservationResponse> responses = getAllReservationService.execute(floor, period);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{reservation_id}")
    public ResponseEntity<LookUpReservationDetailResponse> lookUpReservation(@PathVariable("reservation_id") Long reservationId){
        LookUpReservationDetailResponse response = getReservationService.execute(reservationId);
        return ResponseEntity.ok(response);
    }

}
