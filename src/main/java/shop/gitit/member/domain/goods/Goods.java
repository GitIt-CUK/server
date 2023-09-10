package shop.gitit.member.domain.goods;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.gitit.member.exception.PointViolationException;

@Getter
public class Goods {
    private static final int ZERO = 0;

    @Field(name = "member_commit_count")
    private int point;

    @Builder
    public Goods(int point) {
        this.point = point;
    }

    public void addPointByCommit(int addCommit) {
        this.point += addCommit * 10;
    }

    public void subtractPoint(int subPoint) {
        if (this.point < subPoint || subPoint < ZERO) {
            throw new PointViolationException();
        }
        this.point -= subPoint;
    }
}
