package shop.gitit.member.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResDto {

    private String githubId;
    private String email;
    private String name;
    private String imageUrl;
}
