package shop.gitit.member.service.port.in;

import shop.gitit.member.service.dto.response.GetMemberProfileResDto;

public interface GetProfileUsecase {

    GetMemberProfileResDto getMemberProfile(Long memberId);
}
