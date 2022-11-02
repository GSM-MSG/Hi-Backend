package msg.team1.Hi.domain.personal.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.personal.dto.PersonalUserDto;
import msg.team1.Hi.domain.personal.entity.Personal;
import msg.team1.Hi.domain.personal.repository.PersonalRepository;
import msg.team1.Hi.domain.user.dto.HomeBaseRegisterUserDto;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService{

    private final PersonalRepository personalRepository;
    private final UserRepository userRepository;

    @Override
    public PersonalUserDto findPersonalById(Integer user_idx) {
        Optional<User> user = userRepository.findById(user_idx);

        Personal personal = new Personal(user.get());
        personalRepository.save(personal);

        return new PersonalUserDto(user.get().getName() , user.get().getNumber());
    }

    @Override
    public void savePerson(User user) {
        Personal personal = new Personal(user);
        personalRepository.save(personal);
    }

    @Override
    public HomeBaseRegisterUserDto getRepUser(User user) {
        HomeBaseRegisterUserDto homeBaseRegisterUserDto =
                new HomeBaseRegisterUserDto(user.getName() , user.getNumber());
        return homeBaseRegisterUserDto;
    }

}
