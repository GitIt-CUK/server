package shop.gitit.github.domain;

import static shop.gitit.core.exception.ExceptionEnum.IS_NULL;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import shop.gitit.core.baseentity.BaseEntity;
import shop.gitit.github.domain.commitcount.CommitCount;
import shop.gitit.github.domain.grasscolor.GrassColor;
import shop.gitit.github.domain.tier.Tier;

@Entity
@Table(name = "GITHUB_INFO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GitHubInfo extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "github_info_id")
    private Long id;

    @Column(name = "github_info_member_id", nullable = false)
    private Long memberId;

    @Embedded
    @AttributeOverride(
            name = "count",
            column = @Column(name = "github_info_commit_count", nullable = false))
    private CommitCount commitCount;

    @Column(name = "github_info_tier")
    private Tier tier;

    @Column(name = "github_info_grass_color")
    private GrassColor grassColor;

    public GitHubInfo(Long memberId) {
        Assert.notNull(memberId, IS_NULL.getMessage());
        this.memberId = memberId;
        this.commitCount = new CommitCount();
        this.tier = Tier.init();
        this.grassColor = GrassColor.init();
    }

    public void changeGrassColor(String code) {
        this.grassColor = GrassColor.findColorByCode(code);
    }

    public void updateCommitCount(int commitCount) {
        this.commitCount.update(commitCount);
        this.tier = Tier.updateTier(commitCount);
    }
}
