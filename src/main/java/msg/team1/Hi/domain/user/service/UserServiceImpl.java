package msg.team1.Hi.domain.user.service;


import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;


}
