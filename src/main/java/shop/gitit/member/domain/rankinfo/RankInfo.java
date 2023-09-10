package shop.gitit.member.domain.rankinfo;

import static shop.gitit.member.domain.rankinfo.Tier.updateTier;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.gitit.member.exception.CommitCountViolationException;

@Getter
public class RankInfo {

    private static final int ZERO = 0;

    @Field(name = "member_commit_count")
    private int commitCount;

    @Field(name = "member_tier")
    private Tier tier;

    @Builder
    public RankInfo() {
        this.commitCount = ZERO;
        this.tier = Tier.IRON;
    }

    public int updateRankInfo(int commitCount) {
        int addCommit = commitCount - this.commitCount;
        validateCommitCountRange(commitCount);
        this.commitCount = commitCount;
        this.tier = updateTier(commitCount);
        return addCommit;
    }

    private void validateCommitCountRange(int commitCount) {
        if (commitCount < ZERO) {
            throw new CommitCountViolationException();
        }
    }
}
