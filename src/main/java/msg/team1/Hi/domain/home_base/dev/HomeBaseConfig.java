package msg.team1.Hi.domain.home_base.dev;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.home_base.repository.HomeBaseRepository;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HomeBaseConfig {
    private final HomeBaseRepository homeBaseRepository;

    @PostConstruct
    private void homeBaseSetting() {
        homeBaseRepository.deleteAll();
        log.info("===== HomeBase Setting Success =====");
    }
}
