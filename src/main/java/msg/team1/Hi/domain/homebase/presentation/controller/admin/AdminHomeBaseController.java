package msg.team1.Hi.domain.homebase.presentation.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.homebase.presentation.dto.response.LookUpReservationDetailResponse;
import msg.team1.Hi.domain.homebase.presentation.dto.response.LookUpReservationResponse;
import msg.team1.Hi.domain.homebase.service.ReserveHomeBaseService;
import msg.team1.Hi.domain.reservation.service.GetAllReservationService;
import msg.team1.Hi.domain.reservation.service.GetReservationService;
import msg.team1.Hi.domain.reservation.service.UpdateReservationTeamNameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/home-base")
public class AdminHomeBaseController {

    private final ReserveHomeBaseService reserveHomeBaseService;
    private final GetAllReservationService getAllReservationService;
    private final GetReservationService getReservationService;
    private final UpdateReservationTeamNameService updateReservationTeamNameService;

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveHomeBase(@Valid @RequestBody ReserveHomeBaseRequest request) {
        reserveHomeBaseService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

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

    @PatchMapping("/{reservation_id}/name")
    public ResponseEntity<Void> updateReservationTeamName(@PathVariable("reservation_id") Long reservationId, @RequestParam String teamName) {
        updateReservationTeamNameService.execute(reservationId, teamName);
        return ResponseEntity.noContent().build();
    }
}
