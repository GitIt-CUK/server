package shop.gitit.member.domain.tier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.support.fixture.member.tier.TierFixture;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.gitit.member.domain.tier.Tier.*;

class TierTest {

    @DisplayName("최초 티어는 아이언이다.")
    @Test
    void input0() {
        // given
        Tier tier = init();

        // when

        // then
        assertThat(tier).isEqualTo(IRON);
    }

    @DisplayName("커밋 수가 103이면 골드2다.")
    @Test
    void input103() {
        // given
        int commitCount = TierFixture.commitCountOf(103);

        // when
        Tier result = updateTier(commitCount);

        // then
        assertThat(result).isEqualTo(GOLD2);
    }

    @DisplayName("커밋 수가 20억이면 GOAT다.")
    @Test
    void input200000000() {
        // given
        int commitCount = TierFixture.commitCountOf(2000000000);

        // when
        Tier result = updateTier(commitCount);

        // then
        assertThat(result).isEqualTo(GOAT);
    }
}
