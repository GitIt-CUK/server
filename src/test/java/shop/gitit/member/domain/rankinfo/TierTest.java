package shop.gitit.member.domain.rankinfo;

import static org.assertj.core.api.Assertions.assertThat;
import static shop.gitit.member.domain.rankinfo.Tier.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TierTest {

    @DisplayName("커밋 수가 0이면 아이언이다")
    @Test
    void input0() {
        // given
        int commitCount = 0;

        // when
        Tier result = updateTier(commitCount);

        // then
        assertThat(result).isEqualTo(IRON);
    }

    @DisplayName("커밋 수가 103이면 골드2다")
    @Test
    void input103() {
        // given
        int commitCount = 103;

        // when
        Tier result = updateTier(commitCount);

        // then
        assertThat(result).isEqualTo(GOLD2);
    }

    @DisplayName("커밋 수가 20억이면 GOAT다")
    @Test
    void input200000000() {
        // given
        int commitCount = 2000000000;

        // when
        Tier result = updateTier(commitCount);

        // then
        assertThat(result).isEqualTo(GOAT);
    }
}
