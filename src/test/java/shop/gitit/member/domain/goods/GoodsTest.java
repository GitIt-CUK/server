package shop.gitit.member.domain.goods;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.PointViolationException;

class GoodsTest {
    private final Goods goods = Goods.builder().build();

    @DisplayName("커밋 수에 따라 포인트가 더해진다")
    @Test
    void addPointByCommitSuccess() {
        // given
        int addCommit = 3;

        // when
        goods.addPointByCommit(addCommit);

        // then
        assertThat(goods.getPoint()).isEqualTo(30);
    }

    @DisplayName("음수 포인트를 감소하면 에러가 발생한다.")
    @Test
    void subtractPointNegative() {
        // given
        int subPoint = -50;

        // then
        assertThatThrownBy(() -> goods.subtractPoint(subPoint))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("보유한 포인트보다 많은 포인트를 감소하면 에러가 발생한다.")
    @Test
    void subtractPointBigger() {
        // given
        int subPoint = 100;

        // then
        assertThatThrownBy(() -> goods.subtractPoint(subPoint))
                .isInstanceOf(PointViolationException.class);
    }

    @DisplayName("포인트가 정상적으로 감소한다")
    @Test
    void subtractPointSuccess() {
        // given
        int subPoint = 0;

        // when
        goods.subtractPoint(subPoint);

        // then
        assertThat(goods.getPoint()).isEqualTo(0);
    }
}
