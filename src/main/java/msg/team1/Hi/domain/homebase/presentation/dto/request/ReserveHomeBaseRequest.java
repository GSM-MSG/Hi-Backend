package msg.team1.Hi.domain.homebase.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveHomeBaseRequest {
    @NotBlank
    private String teamName;
    @NotNull
    private Integer floor;
    @NotNull
    private Integer period;
    @NotEmpty
    private List<Long> members;
}
