package msg.team1.Hi.domain.member.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.auth.entity.RefreshToken;
import msg.team1.Hi.domain.auth.exception.RefreshTokenNotFoundException;
import msg.team1.Hi.domain.auth.repository.RefreshTokenRepository;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.annotation.TransactionalService;
import msg.team1.Hi.global.util.MemberUtil;

@TransactionalService
@RequiredArgsConstructor
public class WithdrawService {

    private final MemberUtil memberUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    public void execute(String password) {
        Member member = memberUtil.currentMember();
        RefreshToken refreshToken = refreshTokenRepository.findById(member.getEmail())
                .orElseThrow(() -> new RefreshTokenNotFoundException("존재하지 않는 리프레시 토큰입니다."));
        memberUtil.checkPassword(member, password);

        memberRepository.delete(member);
        refreshTokenRepository.delete(refreshToken);
    }
}
