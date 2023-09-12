package shop.gitit.member.domain.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.PointViolationException;
import shop.gitit.support.fixture.member.point.PointFixture;

class PointTest {

    private final Point point = PointFixture.getPoint();

    @DisplayName("최초 포인트는 0이다.")
    @Test
    void initPoint() {
        // given

        // when

        // then
        assertThat(point.getCoin()).isZero();
    }

    @DisplayName("현재 포인트보다 더 많은 포인트를 소모하면 에러를 던진다.")
    @Test
    void minusOverPoint() {
        // given
        int subPoint = point.getCoin() + PointFixture.pointOf(10);

        // when

        // then
        assertThatThrownBy(() -> point.minus(subPoint)).isInstanceOf(PointViolationException.class);
    }

    @DisplayName("음수 포인트를 소모하면 에러를 던진다.")
    @Test
    void minusNegativePoint() {
        // given
        int subPoint = PointFixture.pointOf(-10);

        // when

        // then
        assertThatThrownBy(() -> point.minus(subPoint)).isInstanceOf(PointViolationException.class);
    }

    @DisplayName("포인트를 소모한다.")
    @Test
    void minusPoint() {
        // given
        int plusPoint = PointFixture.pointOf(10);
        int subPoint = PointFixture.pointOf(5);

        // when
        point.plus(plusPoint);
        point.minus(subPoint);

        // then
        assertThat(point.getCoin()).isEqualTo(plusPoint - subPoint);
    }
}
