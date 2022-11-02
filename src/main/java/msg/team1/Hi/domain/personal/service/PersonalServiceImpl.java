package msg.team1.Hi.domain.personal.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.personal.entity.Personal;
import msg.team1.Hi.domain.personal.repository.PersonalRepository;
import msg.team1.Hi.domain.user.dto.UserDto;
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
    public PersonalUserDto makePersonal(UserDto user) {
        return new PersonalUserDto(user.getName(), user.getNumber());
    }
}
