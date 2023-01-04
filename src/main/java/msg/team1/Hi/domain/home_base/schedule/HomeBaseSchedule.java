package msg.team1.Hi.domain.home_base.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomeBaseSchedule {

    private final HomeBaseSchedule homeBaseSchedule;

    @Scheduled(cron = "0 0 2 ? * MON-FRI")
    public void resetHomeBase() {

    }
}
