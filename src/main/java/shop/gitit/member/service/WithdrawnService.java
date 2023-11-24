package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.port.in.WithdrawnUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class WithdrawnService implements WithdrawnUsecase {

    private final MemberRepository memberRepository;

    @Override
    public void withdrawMember(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NoMemberException::new);
        member.withdrawn();
    }
}
