package shop.gitit.member.controller.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UpdateMemberNickNameReq {

    private String nickname;
}
