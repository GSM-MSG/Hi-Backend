package msg.team1.Hi.domain.personal.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.personal.entity.Personal;
import msg.team1.Hi.domain.personal.repository.PersonalRepository;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final UserRepository userRepository;

    @Override
    public PersonalUserDto savePersonal(Integer user_idx) {
        Optional<User> user = userRepository.findById(user_idx);

        Personal personal = new Personal(user.get());
        personalRepository.save(personal);

        return new PersonalUserDto(user.get().getName() , user.get().getNumber());
    }
}
