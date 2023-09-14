package shop.gitit.member.controller.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMemberNickNameReq {
    private String nickName;

    @Builder
    public UpdateMemberNickNameReq(String nickName) {
        this.nickName = nickName;
    }
}
