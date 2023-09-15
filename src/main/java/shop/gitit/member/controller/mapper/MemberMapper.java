package shop.gitit.member.controller.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.gitit.member.controller.request.UpdateMemberNickNameReq;
import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class MemberMapper {

    public static UpdateMemberNickNameReqDto toUpdateMemberNickNameReqDto(
            long memberId, UpdateMemberNickNameReq req) {
        return UpdateMemberNickNameReqDto.builder()
                .memberId(memberId)
                .nickName(req.getNickName())
                .build();
    }
}
