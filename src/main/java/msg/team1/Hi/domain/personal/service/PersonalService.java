package msg.team1.Hi.domain.personal.service;

import msg.team1.Hi.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalService {

    Integer setPersonal(User user);
}