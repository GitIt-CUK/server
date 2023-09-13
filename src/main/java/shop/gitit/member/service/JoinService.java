package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.usecase.JoinUsecase;

@Service
@RequiredArgsConstructor
public class JoinService implements JoinUsecase {

    private final MemberRepository memberRepository;
}
