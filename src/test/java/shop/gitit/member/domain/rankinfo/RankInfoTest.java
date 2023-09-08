package shop.gitit.member.domain.rankinfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static shop.gitit.member.domain.rankinfo.Tier.BRONZE1;
import static shop.gitit.member.domain.rankinfo.Tier.IRON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.member.exception.CommitCountViolationException;

class RankInfoTest {

    private final RankInfo rankInfo = new RankInfo();

    @DisplayName("RankInfo 객체를 생성하면 누적 커밋수는 0, 티어는 아이언이다")
    @Test
    void initRankInfo() {
        // given

        // when
        int commitCount = rankInfo.getCommitCount();
        Tier tier = rankInfo.getTier();

        // then
        assertThat(commitCount).isEqualTo(0);
        assertThat(tier).isEqualTo(IRON);
    }

    @DisplayName("커밋 수가 음수면 에러를 던진다")
    @Test
    void commitCount0() {
        // given
        int commitCount = -1;

        // then
        assertThatThrownBy(() -> rankInfo.updateRankInfo(commitCount))
                .isInstanceOf(CommitCountViolationException.class);
    }

    @DisplayName("커밋 수가 양수라면 정보가 수정된다")
    @Test
    void updateRankInfo() {
        // given
        int commitCount = 10;

        // when
        rankInfo.updateRankInfo(commitCount);

        // then
        assertThat(rankInfo)
                .extracting(RankInfo::getCommitCount, RankInfo::getTier)
                .contains(commitCount, BRONZE1);
    }
}
