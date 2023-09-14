package shop.gitit.github.domain.grasscolor;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.github.support.githubinfo.grasscolor.GrassColorFixture;

class GrassColorTest {

    @DisplayName("목록에 없는 색을 입력받으면 에러를 던진다.")
    @Test
    void noColor() {
        // given
        String code = GrassColorFixture.noColor();
        // when

        // then
        assertThatThrownBy(() -> GrassColor.findColorByCode(code))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
