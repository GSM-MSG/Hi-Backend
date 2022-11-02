package msg.team1.Hi.domain.homebase.service;

import msg.team1.Hi.domain.homebase.dto.HomebaseDto;
import msg.team1.Hi.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface HomeBaseService {
    HomebaseDto findHomebaseDtoById(Integer homebase_idx);

}
