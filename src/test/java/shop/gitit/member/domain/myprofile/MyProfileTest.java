package shop.gitit.member.domain.myprofile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.NicknameLengthViolationException;

class MyProfileTest {

    private final MyProfile myProfile = new MyProfile();

    @DisplayName("닉네임이 널값이면 에러가 발생한다")
    @Test
    void nicknameIsNull() {
        // given
        String nickname = null;

        // then
        assertThatThrownBy(() -> myProfile.updateNickname(nickname))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("닉네임이 빈 문자열이면 에러가 발생한다")
    @Test
    void nicknameIsEmpty() {
        // given
        String nickname = "";

        // then
        assertThatThrownBy(() -> myProfile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 7글자 이상이면 에러가 발생한다")
    @Test
    void nicknameLengthOverflow() {
        // given
        String nickname = "일곱글자닉네임";

        // then
        assertThatThrownBy(() -> myProfile.updateNickname(nickname))
                .isInstanceOf(NicknameLengthViolationException.class);
    }

    @DisplayName("닉네임이 정상적으로 설정된다")
    @Test
    void updateNicknameSuccess() {
        // given
        String nickname = "정상닉네임";

        // when
        myProfile.updateNickname(nickname);

        // then
        assertThat(myProfile.getNickname()).isEqualTo(nickname);
    }
}
