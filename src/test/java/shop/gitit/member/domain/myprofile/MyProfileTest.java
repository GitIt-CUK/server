package shop.gitit.member.domain.myprofile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.NicknameLengthViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyProfileTest {

    private final MyProfile profile = MyProfile.builder()
            .nickname("닉네임")
            .githubId("깃허브아이디")
            .build();

    @DisplayName("빈 문자열로 닉네임을 교체하면 에러를 던진다.")
    @Test
    void emptyNickname() {
        // given
        String nickname = "";

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 null이면 에러를 던진다.")
    @Test
    void nullNickname() {
        // given
        String nickname = null;

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 6글자를 초과하면 에러를 던진다.")
    @Test
    void maximumNickname() {
        // given
        String nickname = "일곱글자닉네임";

        // when

        // then
        assertThatThrownBy(() -> profile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("정상적인 닉네임 변경시 성공한다.")
    @Test
    void normalNickname() {
        // given
        String nickname = "정상닉네임";

        // when
        profile.updateNickname(nickname);

        // then
        assertThat(profile.getNickname()).isEqualTo(nickname);
    }
}