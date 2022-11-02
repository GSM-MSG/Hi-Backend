package msg.team1.Hi.domain.homebase.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.homebase.dto.HomebaseDto;
import msg.team1.Hi.domain.homebase.entity.Homebase;
import msg.team1.Hi.domain.homebase.repository.HomeBaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeBaseServiceImpl implements HomeBaseService {

    private final HomeBaseRepository homeBaseRepository;

    @Override
    public HomebaseDto homeBaseInfo (Integer homebase_idx) {
        Optional<Homebase> findHomeBase = homeBaseRepository.findById(homebase_idx);
        Integer findHomeBase_idx = findHomeBase.get().getHomebase_idx();

        HomebaseDto homebaseDto = new HomebaseDto();

        if(findHomeBase_idx <= 4 && findHomeBase_idx > 0){
            homebaseDto.setFloor(2);
        } else if(findHomeBase_idx <= 8 && findHomeBase_idx > 4 ) {
            homebaseDto.setFloor(3);
        } else if (findHomeBase_idx <= 12 && findHomeBase_idx > 8) {
            homebaseDto.setFloor(4);
        }
        return homebaseDto;
    }
}
