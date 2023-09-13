package shop.gitit.github.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import shop.gitit.github.domain.grasscolor.GrassColor;
import shop.gitit.github.domain.memberid.MemberId;
import shop.gitit.github.domain.tier.Tier;

class GitHubInfoTest {

    private final GitHubInfo gitHubInfo = new GitHubInfo(new MemberId(1L));

    @DisplayName("최초 잔디 색은 GREEN이다.")
    @Test
    void initGrassColor() {
        // given
        String code = GrassColor.GREEN.getCode();

        // when

        // then
        assertThat(gitHubInfo.getGrassColor()).isEqualTo(GrassColor.GREEN);
    }

    @DisplayName("잔디 컬러 코드를 통해 색을 변경할 수 있다.")
    @Test
    void changeGrassColor() {
        // given
        String code = GrassColor.DIAMOND.getCode();

        // when
        gitHubInfo.changeGrassColor(code);

        // then
        assertThat(gitHubInfo.getGrassColor()).isEqualTo(GrassColor.DIAMOND);
    }

    @DisplayName("누적 커밋 수를 갱신하면 자동으로 승급한다.")
    @Test
    void updateCommitCount() {
        // given
        int commitCount = 300;

        // when
        gitHubInfo.updateCommitCount(commitCount);

        // then
        assertThat(gitHubInfo.getCommitCount().getCount()).isEqualTo(commitCount);
        assertThat(gitHubInfo.getTier()).isEqualTo(Tier.DIAMOND3);
    }
}
