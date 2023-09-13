package shop.gitit.member.domain.memberprofile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.domain.support.member.memberprofile.MemberProfileFixture;
import shop.gitit.member.exception.NicknameLengthViolationException;

class MemberProfileTest {

    private final MemberProfile profile = MemberProfileFixture.getMyProfile();

    @DisplayName("빈 문자열로 닉네임을 교체하면 에러를 던진다.")
    @Test
    void emptyNickname() {
        // given
        String nickname = MemberProfileFixture.emptyNickname();

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 null이면 에러를 던진다.")
    @Test
    void nullNickname() {
        // given
        String nickname = MemberProfileFixture.nullNickname();

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 6글자를 초과하면 에러를 던진다.")
    @Test
    void maximumNickname() {
        // given
        String nickname = MemberProfileFixture.lengthOfNickname(7);

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("정상적인 닉네임 변경시 성공한다.")
    @Test
    void normalNickname() {
        // given
        String nickname = MemberProfileFixture.lengthOfNickname(6);

        // when
        profile.updateNickname(nickname);

        // then
        assertThat(profile.getNickname()).isEqualTo(nickname);
    }
}
