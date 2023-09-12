package shop.gitit.member.domain.grasscolor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.support.fixture.member.grasscolor.GrassColorFixture;

class GrassColorTest {

    @DisplayName("목록에 없는 색을 입력받으면 에러를 던진다.")
    @Test
    void noColor() {
        // given
        String code = GrassColorFixture.noColor();
        // when

        // then
        Assertions.assertThatThrownBy(() -> GrassColor.findColor(code))
                .isInstanceOf(IllegalArgumentException.class);
    }
}