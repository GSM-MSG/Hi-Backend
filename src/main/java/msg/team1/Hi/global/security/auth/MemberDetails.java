package msg.team1.Hi.global.security.auth;

import lombok.Getter;
import msg.team1.Hi.domain.member.entity.Member;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;


@Getter
public class MemberDetails extends User{
    private final Member member;

    public MemberDetails(Member member) {
        super(member.getEmail(), member.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        this.member = member;
    }
}
