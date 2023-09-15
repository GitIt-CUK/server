package shop.gitit.member.service.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberNickNameReqDto {

    private long memberId;
    private String nickName;
}
