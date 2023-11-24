package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.port.in.UpdateNickNameUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateNickNameService implements UpdateNickNameUsecase {

    private final MemberRepository memberRepository;

    @Override
    public UpdateMemberNickNameResDto updateNickName(UpdateMemberNickNameReqDto dto) {
        Member member =
                memberRepository.findById(dto.getMemberId()).orElseThrow(NoMemberException::new);
        member.getProfile().updateNickname(dto.getNickname());
        return UpdateMemberNickNameResDto.builder()
                .nickname(member.getProfile().getNickname())
                .build();
    }
}
