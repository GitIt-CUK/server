package shop.gitit.member.service.port.in;

import shop.gitit.member.service.dto.request.UpdateMemberNickNameReqDto;
import shop.gitit.member.service.dto.response.UpdateMemberNickNameResDto;

public interface UpdateNickNameUsecase {

    UpdateMemberNickNameResDto updateNickName(UpdateMemberNickNameReqDto dto);
}
