package shop.gitit.github.domain.commitcount;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.github.exception.CommitCountViolationException;
import shop.gitit.support.fixture.githubinfo.commitcount.CommitCountFixture;

class CommitCountTest {

    private final CommitCount commitCount = CommitCountFixture.getCommitCount();

    @DisplayName("최초 누적 커밋 수는 0이다.")
    @Test
    void initCommitCount() {
        // given

        // when

        // then
        assertThat(commitCount.getCount()).isZero();
    }

    @DisplayName("현재보다 더 적은 커밋수로 갱신한다면 에러를 던진다.")
    @Test
    void lessThanNowCommitCount() {
        // given
        commitCount.update(10);
        int lessCount = commitCount.getCount() - 5;

        // when

        // then
        assertThatThrownBy(() -> commitCount.update(lessCount))
                .isInstanceOf(CommitCountViolationException.class);
    }

    @DisplayName("0 이상의 커밋 수가 추가된다면 갱신한다.")
    @Test
    void updateCommitCount() {
        // given
        int updatedCount = 10;

        // when
        commitCount.update(updatedCount);

        // then
        assertThat(commitCount.getCount()).isEqualTo(updatedCount);
    }
}
