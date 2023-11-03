package shop.gitit.member.service.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubUserInfo {

    private String githubId;
    private String email;
    private String nickname;
    private String profileImg;
}
