package shop.gitit.member.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.PointViolationException;
import shop.gitit.support.fixture.member.point.PointFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    private final Point point = PointFixture.getPoint();
    private final int POINT_PER_COMMIT = 10;

    @DisplayName("최초 포인트는 0이다.")
    @Test
    void initPoint() {
        // given

        // when

        // then
        assertThat(point.getPoint()).isZero();
    }

    @DisplayName("추가된 커밋마다 10포인트가 적립된다.")
    @Test
    void plusPoint() {
        // given
        int commitCount = PointFixture.commitCountOf(10);

        // when
        point.plus(commitCount);

        // then
        assertThat(point.getPoint()).isEqualTo(commitCount * POINT_PER_COMMIT);
    }

    @DisplayName("현재 포인트보다 더 많은 포인트를 소모하면 에러를 던진다.")
    @Test
    void minusOverPoint() {
        // given
        int subPoint = point.getPoint() + PointFixture.subPointOf(10);

        // when

        // then
        assertThatThrownBy(() -> point.minus(subPoint))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("음수 포인트를 소모하면 에러를 던진다.")
    @Test
    void minusNegativePoint() {
        // given
        int subPoint = PointFixture.subPointOf(-10);

        // when

        // then
        assertThatThrownBy(() -> point.minus(subPoint))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("포인트를 소모한다.")
    @Test
    void minusPoint() {
        // given
        int commitCount = PointFixture.commitCountOf(10);
        int subPoint = PointFixture.subPointOf(10);

        // when
        point.plus(commitCount);
        point.minus(subPoint);

        // then
        assertThat(point.getPoint()).isEqualTo(commitCount * POINT_PER_COMMIT - subPoint);
    }
}