package msg.team1.Hi.domain.personal.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.personal.repository.PersonalRepository;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final UserRepository userRepository;

    /*
    PersonalUserDto 설정
    Personal PK 반환
     */
    @Override
    public Integer setPersonal(User user) {
        PersonalUserDto personalUserDto =
                new PersonalUserDto(user.getName(), user.getNumber(), user.getAuthorization());

        return user.getUser_idx();
    }
}
