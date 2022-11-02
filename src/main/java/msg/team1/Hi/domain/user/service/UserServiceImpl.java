package msg.team1.Hi.domain.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.domain.user.dto.request.SignUpRequest;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public boolean login(User user) {

        Optional<User> findUser = userRepository.findById(user.getUser_idx());

        if(findUser.get() == null) {
            log.info("등록되어있지 않은 유저입니다.");
            return false;
        }

        if(!user.getPassword().equals(findUser.get().getPassword())) {
            log.info("비밀번호가 일치하지 않습니다.");
            return false;
        }

        return true;
    }

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        if(userRepository.findByEmail(signUpRequest.getEmail()).isPresent()){
            // throws new Exception........ 할 예정
        }

        userRepository.save(signUpRequest.toEntity(signUpRequest.getPassword()));
    }

}
