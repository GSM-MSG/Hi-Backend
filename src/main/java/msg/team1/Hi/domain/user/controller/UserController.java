package msg.team1.Hi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.global.security.dto.JwtRequest;
import msg.team1.Hi.domain.user.service.UserService;
import msg.team1.Hi.global.security.dto.JwtResponseDto;
import msg.team1.Hi.global.security.dto.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public String signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtResponseDto userLogin(@RequestBody JwtRequest request) {
        try {
            return userService.login(request);
        } catch (Exception e) {
            return new JwtResponseDto(e.getMessage());
        }
    }
}
