package msg.team1.Hi.domain.home_base.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReserveHomeBaseRequest {
    @NotEmpty
    private final String stair;
    @NotNull
    private final List<Integer> members;
    @NotNull
    private final Integer representativeId;
}
