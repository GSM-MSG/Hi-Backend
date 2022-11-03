package msg.team1.Hi.domain.personal.service;

import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.user.dto.HomeBaseRegisterUserDto;
import msg.team1.Hi.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface PersonalService {

    PersonalUserDto findPersonalById(Integer user_idx);
    void savePerson(User user);
    HomeBaseRegisterUserDto getRepUser(User user);
}
