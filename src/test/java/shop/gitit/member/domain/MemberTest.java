package shop.gitit.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.PointViolationException;
import shop.gitit.support.fixture.member.MemberFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shop.gitit.member.domain.grasscolor.GrassColor.GREEN;
import static shop.gitit.member.domain.tier.Tier.IRON;

class MemberTest {

    private final Member member = MemberFixture.getMember();

    @DisplayName("최초 회원의 포인트는 0이다.")
    @Test
    void firstPoint() {
        // given

        // when

        // then
        assertThat(member.getPoint()).isZero();
    }

    @DisplayName("최초 회원의 티어는 아이언이다.")
    @Test
    void firstTier() {
        // given

        // when

        // then
        assertThat(member.getTier()).isEqualTo(IRON);
    }

    @DisplayName("최초 회원의 잔디 색깔은 GREEN이다.")
    @Test
    void firstGrassColor() {
        // given

        // when

        // then
        assertThat(member.getGrassColor()).isEqualTo(GREEN);
    }

    @DisplayName("음수 포인트를 더하면 에러를 던진다.")
    @Test
    void plusNegativePoint() {
        // given
        int point = MemberFixture.negativePoint();

        // when

        // then
        assertThatThrownBy(() -> member.plusPoint(point))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("양수 포인트를 더하면 적립된다.")
    @Test
    void plusPositivePoint() {
        // given
        int point = MemberFixture.positivePoint();

        // when
        member.plusPoint(point);

        // then
        assertThat(member.getPoint()).isEqualTo(point);
    }

    @DisplayName("음수 포인트를 빼면 에러를 던진다.")
    @Test
    void minusNegativePoint() {
        // given
        int point = MemberFixture.negativePoint();

        // when

        // then
        assertThatThrownBy(() -> member.minusPoint(point))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("현재 포인트보다 더 큰 수를 빼면 에러를 던진다.")
    @Test
    void minusMaximumPoint() {
        // given
        int point = member.getPoint() + MemberFixture.positivePoint();

        // when

        // then
        assertThatThrownBy(() -> member.minusPoint(point))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("포인트를 소모한다.")
    @Test
    void minusNormalPoint() {
        // given
        int plusPoint = MemberFixture.positivePoint();
        int subPoint = MemberFixture.pointOf(5);

        // when
        member.plusPoint(plusPoint);
        member.minusPoint(subPoint);

        // then
        assertThat(member.getPoint()).isEqualTo(plusPoint - subPoint);
    }
}