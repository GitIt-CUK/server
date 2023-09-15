package shop.gitit.member.service;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;
import shop.gitit.member.service.usecase.UpdateNickNameUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateNickNameService implements UpdateNickNameUsecase {

    private final MemberRepository memberRepository;

    @Override
    public UpdateMemberNickNameResDto updateNickName(UpdateMemberNickNameReqDto dto) {
        Member member =
                memberRepository
                        .findById(dto.getMemberId())
                        .orElseThrow(() -> new NoMemberException("해당 회원이 존재하지 않습니다."));
        member.getProfile().updateNickname(dto.getNickName());
        return UpdateMemberNickNameResDto.builder()
                .nickName(member.getProfile().getNickname())
                .build();
    }
}
