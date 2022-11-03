package msg.team1.Hi.domain.user.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msg.team1.Hi.global.security.authentication.UserDetailsImpl;
import msg.team1.Hi.global.security.dto.JwtRequest;
import msg.team1.Hi.global.security.dto.SignUpRequest;
import msg.team1.Hi.domain.user.entity.User;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public String login(JwtRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return principal.getUsername();
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
