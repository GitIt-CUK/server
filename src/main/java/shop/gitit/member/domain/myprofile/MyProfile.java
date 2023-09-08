package shop.gitit.member.domain.myprofile;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;
import shop.gitit.member.exception.NicknameLengthViolationException;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyProfile {

    private static final int MAX_NICKNAME_LENGTH = 6;

    @Field(name = "member_github_id")
    private String githubId;

    @Field(name = "member_nickname")
    private String nickname;

    public void updateNickname(String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    private void validateNickname(String nickname) {
        Assert.notNull(nickname, IS_NULL.getMessage());
        if (lengthViolation(nickname)) {
            throw new NicknameLengthViolationException();
        }
    }

    private boolean lengthViolation(String nickname) {
        return nickname.length() > MAX_NICKNAME_LENGTH || nickname.isEmpty();
    }
}
