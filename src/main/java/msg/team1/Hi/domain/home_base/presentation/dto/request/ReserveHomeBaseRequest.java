package msg.team1.Hi.domain.home_base.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReserveHomeBaseRequest {
    @NotEmpty
    private String teamName;
    @NotEmpty
    private Integer floor;
    @NotEmpty
    private Integer period;
    @NotEmpty
    private List<Integer> members;
}
