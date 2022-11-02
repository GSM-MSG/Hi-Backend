package msg.team1.Hi.domain.user.service;

import msg.team1.Hi.domain.user.dto.LoginUserDto;
import msg.team1.Hi.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public boolean login(User user);
}
