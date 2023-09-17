package shop.gitit.member.service.usecase;

import shop.gitit.member.service.dto.response.GetMemberProfileResDto;

public interface GetProfileUsecase {

    GetMemberProfileResDto getMemberProfile(Long memberId);
}
