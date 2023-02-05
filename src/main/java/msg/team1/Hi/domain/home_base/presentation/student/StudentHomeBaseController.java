package msg.team1.Hi.domain.home_base.presentation.student;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.home_base.presentation.dto.request.ReserveHomeBaseRequest;
import msg.team1.Hi.domain.home_base.service.HomeBaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("home-base")
public class StudentHomeBaseController {

    private final HomeBaseService homeBaseService;

    @PostMapping("reserve")
    public ResponseEntity<Void> reserveHomeBase(@Valid @RequestBody ReserveHomeBaseRequest request) {
        homeBaseService.reserveHomeBase(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
