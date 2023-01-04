package msg.team1.Hi.domain.home_base.controller.admin;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("home-base/admin")
public class AdminHomeBaseController {

    private final HomeBaseService homeBaseService;

    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveHomeBase(@Valid @RequestBody ReserveHomeBaseRequest request) {
        homeBaseService.reserveHomeBase(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // delete 기능 테스트용 향후 제거할 계
    @DeleteMapping
    public ResponseEntity<Void> resetHomeBaseTestApi() {
        homeBaseService.resetHomeBase();
        return ResponseEntity.ok().build();
    }
}
