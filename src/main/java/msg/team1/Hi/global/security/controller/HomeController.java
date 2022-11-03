package msg.team1.Hi.global.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("student")
    public String student() {
        return "student";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
