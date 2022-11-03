package msg.team1.Hi.domain.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.global.security.dto.JwtRequest;
import msg.team1.Hi.global.security.dto.SignUpRequest;
import msg.team1.Hi.domain.user.dto.response.LoginResponse;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginResponse login(JwtRequest loginRequest) {
        if(userRepository.findByEmail(loginRequest.getEmail()) == null) {
            throw new IllegalArgumentException("존재하지 않는 회원입니다.");
        }

        return null;
    }

    @Override
    @Transactional
    public String signUp(SignUpRequest signUpRequest) {
        if(userRepository.existByEmail(signUpRequest.getEmail())) {
            return null;
        }
        User user = new User(signUpRequest);
        user.encryptPassword(passwordEncoder);

        userRepository.save(user);
        return user.getEmail();
    }

}
