package shop.gitit.member.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.member.domain.Member;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@Service
@RequiredArgsConstructor
public class UpdateNickNameService implements UpdateNickNameUsecase {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UpdateMemberNickNameResDto updateNickName(UpdateMemberNickNameReqDto dto) {
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
        member.getProfile().updateNickname(dto.getNickName());
        return UpdateMemberNickNameResDto.builder()
                .memberId(member.getId())
                .nickName(member.getProfile().getNickname())
                .build();
    }
}
