package shop.gitit.member.domain.goods;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;
import shop.gitit.member.exception.PointViolationException;

@Getter
public class Goods {
    private static final int ZERO = 0;
    private static final int DRAW_COST = 10;

    @Field(name = "member_point")
    private int point;

    @Builder
    public Goods() {
        this.point = ZERO;
    }

    public void addPointByCommit(int addCommit) {
        this.point += addCommit * DRAW_COST;
    }

    public void subtractPoint(int subPoint) {
        if (this.point < subPoint || subPoint < ZERO) {
            throw new PointViolationException();
        }
        this.point -= subPoint;
    }
}
