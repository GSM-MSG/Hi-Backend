package msg.team1.Hi.domain.member.service;

import lombok.RequiredArgsConstructor;
import msg.team1.Hi.domain.member.entity.Member;
import msg.team1.Hi.domain.member.presentation.dto.request.SearchMemberRequest;
import msg.team1.Hi.domain.member.presentation.dto.response.MemberInfoResponse;
import msg.team1.Hi.domain.member.repository.MemberRepository;
import msg.team1.Hi.global.util.MemberUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchMemberService {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;

    public List<MemberInfoResponse> execute(SearchMemberRequest request){
        List<Member> members = memberRepository.findAllByNameContaining(request.getName());
        return memberUtil.memberListToDtoList(members);
    }
}
