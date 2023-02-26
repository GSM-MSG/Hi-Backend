package msg.team1.Hi.domain.home_base.presentation.controller.student;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.home_base.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import msg.team1.Hi.domain.reservation.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/home-base")
public class StudentHomeBaseController {

    private final HomeBaseService homeBaseService;
    private final ReservationService reservationService;

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveHomeBase(@Valid @RequestBody ReserveHomeBaseRequest request) {
        homeBaseService.reserveHomeBase(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<LookUpReservationResponse>> lookUpReservations(@RequestParam Integer floor,
                                                                              @RequestParam Integer period){
        List<LookUpReservationResponse> responses = reservationService.lookUpAllReservation(floor, period);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{reservation_id}")
    public ResponseEntity<LookUpReservationDetailResponse> lookUpReservation(@PathVariable("reservation_id") Long reservationId){
        LookUpReservationDetailResponse response = reservationService.lookUpReservation(reservationId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{reservation_id}/name")
    public ResponseEntity<Void> updateReservationTeamName(@PathVariable("reservation_id") Long reservationId, @RequestParam String teamName){
        reservationService.updateReservation(reservationId, teamName);
        return ResponseEntity.noContent().build();
    }
}
