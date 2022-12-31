package msg.team1.Hi.domain.home_base.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReserveHomeBaseRequest {
    @NotEmpty
    private final String stair;
    @NotEmpty
    private final List<String> members;
    @NotEmpty
    private final String representativeName;
}
