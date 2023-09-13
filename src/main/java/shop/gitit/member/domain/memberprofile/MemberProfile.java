package shop.gitit.member.domain.memberprofile;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import shop.gitit.member.exception.NicknameLengthViolationException;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile {

    @Transient private static final int MAX_NICKNAME_LENGTH = 6;

    private String githubId;

    private String nickname;

    @Builder
    public MemberProfile(String githubId, String nickname) {
        Assert.notNull(githubId, IS_NULL.getMessage());
        Assert.notNull(nickname, IS_NULL.getMessage());
        this.githubId = githubId;
        this.nickname = nickname;
    }

    public void updateNickname(String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    private void validateNickname(String nickname) {
        if (isInvalid(nickname)) {
            throw new NicknameLengthViolationException();
        }
    }

    private boolean isInvalid(String nickname) {
        return nickname == null || nickname.length() > MAX_NICKNAME_LENGTH || nickname.isEmpty();
    }
}
