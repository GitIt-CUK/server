package shop.gitit.member.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResDto {

    private Long memberId;
    private String nickname;
    private String refreshToken;
    private String accessToken;
}
