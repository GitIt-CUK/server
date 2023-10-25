package shop.gitit.member.service.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfileResDto {

    private String githubId;
    private String email;
    private String name;
    private String imageUrl;
}
