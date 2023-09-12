package shop.gitit.github.domain.commitcount;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import lombok.Getter;
import shop.gitit.member.exception.CommitCountViolationException;

@Embeddable
@Getter
public class CommitCount {

    @Transient private static final int ZERO = 0;

    private int count;

    public CommitCount() {
        this.count = ZERO;
    }

    public void update(int commitCount) {
        validateCommitCountRange(commitCount);
        plus(commitCount);
    }

    private void validateCommitCountRange(int commitCount) {
        if (isLessThanNow(commitCount)) {
            throw new CommitCountViolationException();
        }
    }

    private boolean isLessThanNow(int commitCount) {
        return commitCount < this.count;
    }

    private void plus(int commitCount) {
        this.count += commitCount;
    }
}
