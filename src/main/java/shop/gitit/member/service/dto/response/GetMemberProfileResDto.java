package shop.gitit.member.service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMemberProfileResDto {

    private Long memberId;
    private String githubId;
    private String nickname;
    private String profileImg;
    private int commitCount;
    private int commitLimit;
}
