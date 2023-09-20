package shop.gitit.member.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateMemberNickNameResDto {

    private String nickname;
}
